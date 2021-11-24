package com.ihsan.binarchallengechapter7

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.VideoView

class VideoActivity : AppCompatActivity() {
    private val videoView: VideoView by lazy {
        findViewById(R.id.video_view)
    }

    private val btnPlay: ImageButton by lazy {
        findViewById(R.id.btn_play)
    }

    private val btnPause: ImageButton by lazy {
        findViewById(R.id.btn_pause)
    }

    private val btnStop: ImageButton by lazy {
        findViewById(R.id.btn_stop)
    }

    private val btnBack: Button by lazy {
        findViewById(R.id.btn_back)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val pathVideo = "android.resource://$packageName/${R.raw.tutorial}"
        videoView.setVideoURI(Uri.parse(pathVideo))

        btnPlay.setOnClickListener {
            videoView.start()
        }

        btnPause.setOnClickListener {
            videoView.pause()
        }

        btnStop.setOnClickListener {
            videoView.stopPlayback()
            videoView.setVideoURI(Uri.parse(pathVideo))
        }

        btnBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}