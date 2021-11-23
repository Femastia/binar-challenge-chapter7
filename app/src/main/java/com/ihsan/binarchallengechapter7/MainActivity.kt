package com.ihsan.binarchallengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ihsan.binarchallengechapter7.utils.GameMusic

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //develop here

        startService(Intent(this, GameMusic::class.java))
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