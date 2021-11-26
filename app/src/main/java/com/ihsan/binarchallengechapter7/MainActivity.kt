package com.ihsan.binarchallengechapter7

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ihsan.binarchallengechapter7.databinding.ActivityMainBinding
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.utils.GameMusic
import com.ihsan.binarchallengechapter7.video.VideoActivity
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginPref: LoginPref
    companion object {
        const val KEY_NAME = "key_name"
        const val KEY_NAME_FROM_DIALOG = "key_name_from_dialog"
        const val KEY_NAME_FROM_MAIN = "key_name_from_main"
        const val TAG = "MenuActivity"
    }
    private  var nameFromLandingPage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
        loginPref = LoginPref(this)
        startService(Intent(this, GameMusic::class.java))

        mainViewModel.seasonLogin(loginPref.getLoginPref().toString())
        mainViewModel.loginStatus.observe(this, {
            val nameFromLandingPage = it.data?.username
            val nameFromMain = it.data?.username
            val nameFromDialog = it.data?.username
            when {
                nameFromLandingPage != null -> {
                    initFromLandingPage(nameFromLandingPage)
                }
                nameFromMain != null -> {
                    initFromMain(nameFromMain)
                }
                else -> {
                    initFromDialog(nameFromDialog)
                }
            }
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


    private fun initFromDialog(nameFromDialog: String?) {
        battleWithPlayer(nameFromDialog)
        battleWithCom(nameFromDialog)
        showSnackBar(nameFromDialog)
    }

    private fun initFromMain(nameFromMain: String) {
        battleWithPlayer(nameFromMain)
        battleWithCom(nameFromMain)
        showSnackBar(nameFromMain)
    }

    private fun initFromLandingPage(name: String) {
        battleWithPlayer(name)
        battleWithCom(name)
        showSnackBar(name)
    }

    private fun showSnackBar(name: String?) {
        val snackbar =
            Snackbar.make(binding.root, "Selamat Datang $name", Snackbar.LENGTH_INDEFINITE)
        snackbar.apply {
            view.setBackgroundColor(Color.BLACK)
            setAction("Tutup") { dismiss() }
            show()
        }
    }

    private fun battleWithCom(name: String?) {
        binding.imgVsCom.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
    }

    private fun battleWithPlayer(name: String?) {
        binding.imgVsPlayer.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.EXTRA_NAME, name)
            intent.putExtra(GameActivity.EXTRA_VS, "vsplayer")
            startActivity(intent)
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