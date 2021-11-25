package com.ihsan.binarchallengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ihsan.binarchallengechapter7.databinding.ActivityProfileBinding
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var loginPref: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
        loginPref = LoginPref(this)
        mainViewModel.getUser(loginPref.getLoginPref().toString())
        binding.progressBar.visibility = View.VISIBLE
        mainViewModel.getUser.observe(this, {
            binding.progressBar.visibility = View.GONE
            Glide.with(this)
                .load(it.data?.photo)
                .into(binding.imgProfile)
            binding.tvUsername.text = it.data?.username
            binding.tvEmailuser.text = it.data?.email
        })

        binding.tvEditUser.setOnClickListener {
            startActivity(Intent(this, UpdateUserActivity::class.java))
        }
    }
}