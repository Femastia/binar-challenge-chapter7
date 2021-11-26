package com.ihsan.binarchallengechapter7.helper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.ihsan.binarchallengechapter7.GameActivity
import com.ihsan.binarchallengechapter7.R
import com.ihsan.binarchallengechapter7.helper.Utils.PAPER
import com.ihsan.binarchallengechapter7.helper.Utils.ROCK
import com.ihsan.binarchallengechapter7.helper.Utils.SCISSORS
import com.ihsan.binarchallengechapter7.helper.Utils.openDialogResult
import com.ihsan.binarchallengechapter7.helper.Utils.showLogInfo
import com.ihsan.binarchallengechapter7.helper.Utils.showToast
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

open class ResultGameVsPlayer(private val activity: GameActivity) {

    private val setBgChoice = SetBackgroundChoice(activity)
    private val modeSingle = "Singleplayer"
    private val modeMulti = "Multiplayer"
    private val resulWin = "Player Win"
    private val resulOppenentWin = "Opponent Win"
    private val resulDraw = "Draw"

    @RequiresApi(Build.VERSION_CODES.M)
    open fun playerChoice(
        name: String,
        choicePlayer1: String,
        choicePlayer2: String
    ) {
        //DRAW
        if (choicePlayer1 == ROCK && choicePlayer2 == ROCK) {
            setBgChoice.backgroundChoicePlayer1(ROCK)
            setBgChoice.backgroundChoicePlayer2(ROCK)
            activity.binding.imgVs.setImageResource(R.drawable.draw)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulDraw)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "${activity.player2} Memilih $ROCK")
            showLogInfo("MainActivity", "$name: $ROCK, Player2: $ROCK")

        } else if (choicePlayer1 == PAPER && choicePlayer2 == PAPER) {
            setBgChoice.backgroundChoicePlayer1(PAPER)
            setBgChoice.backgroundChoicePlayer2(PAPER)
            activity.binding.imgVs.setImageResource(R.drawable.draw)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulDraw)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $PAPER")
            showLogInfo("MainActivity", "$name: $PAPER, Player2: $PAPER")

        } else if (choicePlayer1 == SCISSORS && choicePlayer2 == SCISSORS) {
            setBgChoice.backgroundChoicePlayer1(SCISSORS)
            setBgChoice.backgroundChoicePlayer2(SCISSORS)
            activity.binding.imgVs.setImageResource(R.drawable.draw)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulDraw)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $SCISSORS")
            showLogInfo("MainActivity", "$name: $SCISSORS, Player2: $SCISSORS")
        }

        //PLAYER 1 WINNER
        if (choicePlayer1 == ROCK && choicePlayer2 == SCISSORS) {
            setBgChoice.backgroundChoicePlayer1(ROCK)
            setBgChoice.backgroundChoicePlayer2(SCISSORS)
            activity.binding.imgVs.setImageResource(R.drawable.you_win)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $SCISSORS")
            showLogInfo("MainActivity", "$name: $ROCK, Player2: $SCISSORS")

        } else if (choicePlayer1 == PAPER && choicePlayer2 == ROCK) {
            setBgChoice.backgroundChoicePlayer1(PAPER)
            setBgChoice.backgroundChoicePlayer2(ROCK)
            activity.binding.imgVs.setImageResource(R.drawable.you_win)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $ROCK")
            showLogInfo("MainActivity", "$name: $PAPER, Player2: $ROCK")

        } else if (choicePlayer1 == SCISSORS && choicePlayer2 == PAPER) {
            setBgChoice.backgroundChoicePlayer1(SCISSORS)
            setBgChoice.backgroundChoicePlayer2(PAPER)
            activity.binding.imgVs.setImageResource(R.drawable.you_win)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $PAPER")
            showLogInfo("MainActivity", "$name: $SCISSORS, Player2: $PAPER")
        }

        //PLAYER 2 WINNER
        if (choicePlayer1 == ROCK && choicePlayer2 == PAPER) {
            setBgChoice.backgroundChoicePlayer1(ROCK)
            setBgChoice.backgroundChoicePlayer2(PAPER)
            activity.binding.imgVs.setImageResource(R.drawable.com_win)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulOppenentWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $PAPER")
            showLogInfo("MainActivity", "$name: $ROCK, Player2: $PAPER")

        } else if (choicePlayer1 == PAPER && choicePlayer2 == SCISSORS) {
            setBgChoice.backgroundChoicePlayer1(PAPER)
            setBgChoice.backgroundChoicePlayer2(SCISSORS)
            activity.binding.imgVs.setImageResource(R.drawable.com_win)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulOppenentWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $SCISSORS")
            showLogInfo("MainActivity", "$name: $PAPER, Player2: $SCISSORS")

        } else if (choicePlayer1 == SCISSORS && choicePlayer2 == ROCK) {
            setBgChoice.backgroundChoicePlayer1(SCISSORS)
            setBgChoice.backgroundChoicePlayer2(ROCK)
            activity.binding.imgVs.setImageResource(R.drawable.com_win)
            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeMulti, resulOppenentWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "Pemain 2 Memilih $ROCK")
            showLogInfo("MainActivity", "$name: $SCISSORS, Player2: $ROCK")
        }
    }
}