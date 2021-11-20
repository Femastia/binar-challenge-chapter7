package com.ihsan.binarchallengechapter7.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ihsan.binarchallengechapter7.MainActivity
import com.ihsan.binarchallengechapter7.R
import com.ihsan.binarchallengechapter7.helper.LoginPref
import com.ihsan.binarchallengechapter7.landingpage.LandingPageActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var loginPref: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        loginPref = LoginPref(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (loginPref.sharedPreferences.contains(LoginPref.IS_LOGIN)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LandingPageActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 3000L) //3000 L = 3 detik
    }
}