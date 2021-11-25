package com.ihsan.binarchallengechapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ihsan.binarchallengechapter7.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private var player1 = -1
    private var player2 = -2

    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO key untuk nama player pada login adalah "nama"
        val nama = intent.getStringExtra("nama") ?: "not found"
        binding.tvP1.text = nama
        //TODO key untuk mode game pada main menu adalah "mode" dengan value "pvp" atau "vscom"
        val mode = intent.getStringExtra("mode") ?: "not found"

        if (mode == "pvp") {
            playModePvp()
        } else {
            playModeVscom()
        }

        binding.ivReset.setOnClickListener {
            btnReseter()
        }

        //TODO buat dialog tombol home (ivHome) dan tombol X (ivClose)
    }

    private fun playModePvp() {
        binding.tvP2.text = "Player 2"
        binding.ivB1.setOnClickListener {
            player1 = 0
            toastLog()

            btnDisabler()
            resultProcess()
        }
        binding.ivB2.setOnClickListener {
            player2 = 0
            toastLogP2()

            btnDisablerP2()
            resultProcess()
        }
        binding.ivG1.setOnClickListener {
            player1 = 1
            toastLog()

            btnDisabler()
            resultProcess()
        }
        binding.ivG2.setOnClickListener {
            player2 = 1
            toastLogP2()

            btnDisablerP2()
            resultProcess()
        }
        binding.ivK1.setOnClickListener {
            player1 = 2
            toastLog()

            btnDisabler()
            resultProcess()
        }
        binding.ivK2.setOnClickListener {
            player2 = 2
            toastLogP2()

            btnDisablerP2()
            resultProcess()
        }
        binding.ivReset.setOnClickListener {
            btnReseter()
            btnEnablerAll()
        }
    }

    private fun playModeVscom() {
        binding.tvP2.text = "CPU"
        btnDisablerP2()
        binding.ivB1.setOnClickListener {
            player1 = 0
            Log.e("Player 1 memilih", "Batu")

            btnDisabler()
            cpuPlay()
            resultProcess()
        }
        binding.ivG1.setOnClickListener {
            player1 = 1
            Log.e("Player 1 memilih", "Gunting")

            btnDisabler()
            cpuPlay()
            resultProcess()
        }
        binding.ivK1.setOnClickListener {
            player1 = 2
            Log.e("Player 1 memilih", "Kertas")

            btnDisabler()
            cpuPlay()
            resultProcess()
        }
        binding.ivReset.setOnClickListener {
            btnReseter()
            btnEnablerP1()
        }
    }

    private fun btnReseter() {
        binding.ivGame.setImageResource(R.drawable.img_vs)
        binding.ivB1.setImageResource(R.drawable.batu)
        binding.ivB2.setImageResource(R.drawable.batu)
        binding.ivG1.setImageResource(R.drawable.gunting)
        binding.ivG2.setImageResource(R.drawable.gunting)
        binding.ivK1.setImageResource(R.drawable.kertas)
        binding.ivK2.setImageResource(R.drawable.kertas)
        player1 = -1
        player2 = -2
    }

    private fun cpuPlay() {
        player2 = Random.nextInt(3)
        when (player2) {
            0 -> {
                binding.ivB2.setImageResource(R.drawable.batu_x)
                Log.e("CPU memilih", "Batu")
            }
            1 -> {
                binding.ivG2.setImageResource(R.drawable.gunting_x)
                Log.e("CPU memilih", "Gunting")
            }
            2 -> {
                binding.ivK2.setImageResource(R.drawable.kertas_x)
                Log.e("CPU memilih", "Kertas")
            }
        }
    }

    private fun resultProcess() {
        if (player1 == player2) {
            binding.ivGame.setImageResource(R.drawable.draw)
            resultRevealer()
        } else if (
            (player1 == 0 && player2 == 1) ||
            (player1 == 1 && player2 == 2) ||
            (player1 == 2 && player2 == 0)
        ) {
            binding.ivGame.setImageResource(R.drawable.p1_win)
            resultRevealer()
        } else if (
            (player1 == 1 && player2 == 0) ||
            (player1 == 2 && player2 == 1) ||
            (player1 == 0 && player2 == 2)
        ) {
            binding.ivGame.setImageResource(R.drawable.p2_win)
            resultRevealer()
        }
    }

    private fun resultRevealer() {
        when (player1) {
            0 -> binding.ivB1.setImageResource(R.drawable.batu_x)
            1 -> binding.ivG1.setImageResource(R.drawable.gunting_x)
            2 -> binding.ivK1.setImageResource(R.drawable.kertas_x)
        }
        when (player2) {
            0 -> binding.ivB2.setImageResource(R.drawable.batu_x)
            1 -> binding.ivG2.setImageResource(R.drawable.gunting_x)
            2 -> binding.ivK2.setImageResource(R.drawable.kertas_x)
        }
    }

    private fun toastLog() {
        Toast.makeText(applicationContext, "Player 1 telah memilih", Toast.LENGTH_SHORT).show()
        when (player1) {
            0 -> Log.e("Player 1 memilih", "Batu")
            1 -> Log.e("Player 1 memilih", "Gunting")
            2 -> Log.e("Player 1 memilih", "Kertas")
        }
    }

    private fun toastLogP2() {
        Toast.makeText(applicationContext, "Player 2 telah memilih", Toast.LENGTH_SHORT).show()
        when (player2) {
            0 -> Log.e("Player 2 memilih", "Batu")
            1 -> Log.e("Player 2 memilih", "Gunting")
            2 -> Log.e("Player 2 memilih", "Kertas")
        }
    }

    private fun btnDisabler() {
        binding.ivB1.isClickable = false
        binding.ivG1.isClickable = false
        binding.ivK1.isClickable = false
    }

    private fun btnDisablerP2() {
        binding.ivB2.isClickable = false
        binding.ivG2.isClickable = false
        binding.ivK2.isClickable = false
    }

    private fun btnEnablerAll() {
        binding.ivB1.isClickable = true
        binding.ivB2.isClickable = true
        binding.ivG1.isClickable = true
        binding.ivG2.isClickable = true
        binding.ivK1.isClickable = true
        binding.ivK2.isClickable = true
    }

    private fun btnEnablerP1() {
        binding.ivB1.isClickable = true
        binding.ivG1.isClickable = true
        binding.ivK1.isClickable = true
    }


}