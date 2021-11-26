package com.ihsan.binarchallengechapter7.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ihsan.binarchallengechapter7.MainActivity
import com.ihsan.binarchallengechapter7.R
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.landingpage.LandingPageActivity
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var loginPref: LoginPref
    private var isLogin: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val mainViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]

        loginPref = LoginPref(this)
        mainViewModel.seasonLogin(loginPref.getLoginPref().toString())
        mainViewModel.errorLogin.observe(this, {
            isLogin = it.status
            if (it.errors == "Token is expired") {
                Toast.makeText(this, "Sesi login berakhiir", Toast.LENGTH_SHORT).show()
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            if (loginPref.getLoginPref().equals("tokenNull") || isLogin == false) {
                val intent = Intent(this, LandingPageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 4000L) //3000 L = 3 detik
    }
}