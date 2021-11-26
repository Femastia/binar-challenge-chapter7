package com.ihsan.binarchallengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ihsan.binarchallengechapter7.databinding.ActivityGameBinding
import com.ihsan.binarchallengechapter7.helper.ResultGameVsCom
import com.ihsan.binarchallengechapter7.helper.ResultGameVsPlayer
import com.ihsan.binarchallengechapter7.helper.SetBackgroundChoice
import com.ihsan.binarchallengechapter7.helper.Utils.PAPER
import com.ihsan.binarchallengechapter7.helper.Utils.ROCK
import com.ihsan.binarchallengechapter7.helper.Utils.SCISSORS

class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding
    private lateinit var namePlayer: String
    private lateinit var versus: String

    var choice1 = ""
    var choice2 = ""
    val player2 = "Player 2"
    val comList = listOf("GUNTING", "BATU", "KERTAS")

    private val setBgChoice = SetBackgroundChoice(this)
    private val resultGameVsPlayer = ResultGameVsPlayer(this)
    private val resultGameVsCom = ResultGameVsCom(this)

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_VS = "extra_vs"
        const val TAG = "GameActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListener()
    }

    override fun onStart() {
        super.onStart()
        namePlayer = intent.getStringExtra(EXTRA_NAME).toString()
        versus = intent.getStringExtra(EXTRA_VS).toString()
        battleWith(versus)
    }

    private fun battleWith(vS: String) {
        if (vS != "vsplayer") {
            binding.imgGunting.setOnClickListener {
                resultGameVsCom.playerChoiceGunting(namePlayer, SCISSORS, comList.random())
            }
            binding.imgBatu.setOnClickListener {
                resultGameVsCom.playerChoiceBatu(namePlayer, ROCK, comList.random())
            }
            binding.imgKertas.setOnClickListener {
                resultGameVsCom.playerChoiceKertas(namePlayer, PAPER, comList.random())
            }
        } else {
            //Player1
            binding.imgBatu.setOnClickListener {
                choice1 = ROCK
                calculateGame()
            }
            binding.imgKertas.setOnClickListener {
                choice1 = PAPER
                calculateGame()
            }
            binding.imgGunting.setOnClickListener {
                choice1 = SCISSORS
                calculateGame()
            }
            //Player2
            binding.imgComBatu.setOnClickListener {
                choice2 = ROCK
                calculateGame()
            }
            binding.imgComKertas.setOnClickListener {
                choice2 = PAPER
                calculateGame()
            }
            binding.imgComGunting.setOnClickListener {
                choice2 = SCISSORS
                calculateGame()
            }
        }
    }

    private fun calculateGame() {
        if (choice1 != "" && choice2 != "") {
            setBgChoice.backgroundChoicePlayer1(choice1)
            setBgChoice.backgroundChoicePlayer2(choice2)
            resultGameVsPlayer.playerChoice(namePlayer, choice1, choice2)
        }
    }

    private fun setOnClickListener() {
        binding.imgClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.KEY_NAME_FROM_MAIN, namePlayer)
            startActivity(intent)
        }
        binding.imgRefresh.setOnClickListener {
            finish()
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.KEY_NAME_FROM_MAIN, namePlayer)
        startActivity(intent)
    }
}