package com.ihsan.binarchallengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.ViewModelProvider
import com.ihsan.binarchallengechapter7.databinding.ActivityMainBinding
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.utils.GameMusic
import com.ihsan.binarchallengechapter7.video.VideoActivity
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginPref: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
        loginPref = LoginPref(this)
        startService(Intent(this, GameMusic::class.java))

        mainViewModel.seasonLogin(loginPref.getLoginPref().toString())
        mainViewModel.loginStatus.observe(this, {
            binding.tvName.text = it.data?.username
            binding.tvName2.text = it.data?.username
        })

        binding.tvKeluar.setOnClickListener {
            loginPref.clearStatusLogin()
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        binding.imgUser.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.imgVidTutorial.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        stopMusic()
        finish()
    }

    private fun stopMusic() {
        stopService(
            Intent(
                this,
                GameMusic::class.java
            )
        )
    }
}