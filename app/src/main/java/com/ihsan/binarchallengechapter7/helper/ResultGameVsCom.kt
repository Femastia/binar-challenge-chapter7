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
import com.ihsan.binarchallengechapter7.helper.Utils.showToast
import com.ihsan.binarchallengechapter7.viewmodel.MainViewModel

open class ResultGameVsCom(private val activity: GameActivity) {

    private val setBgChoice = SetBackgroundChoice(activity)
    private val modeSingle = "Singleplayer"
    private val modeMulti = "Multiplayer"
    private val resulWin = "Player Win"
    private val resulOppenentWin = "Opponent Win"
    private val resulDraw = "Draw"

    @RequiresApi(Build.VERSION_CODES.M)
    open fun playerChoiceGunting(name: String, player: String, com: String) {
        if (player == SCISSORS && com == activity.comList[0]) {
            setBgChoice.backgroundChoicePlayer1(SCISSORS)
            setBgChoice.backgroundChoiceCom(SCISSORS)
            activity.binding.imgVs.setImageResource(R.drawable.draw)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulDraw)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $SCISSORS\nCOM Memilih $SCISSORS")

        } else if (player == SCISSORS && com == activity.comList[1]) {
            setBgChoice.backgroundChoicePlayer1(SCISSORS)
            setBgChoice.backgroundChoiceCom(ROCK)
            activity.binding.imgVs.setImageResource(R.drawable.com_win)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulOppenentWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $SCISSORS\n COM Memilih $ROCK")

        } else if (player == SCISSORS && com == activity.comList[2]) {
            setBgChoice.backgroundChoicePlayer1(SCISSORS)
            setBgChoice.backgroundChoiceCom(PAPER)
            activity.binding.imgVs.setImageResource(R.drawable.you_win)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "$name Memilih $SCISSORS\n COM Memilih $PAPER")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun playerChoiceBatu(name: String, player: String, com: String) {
        if (player == ROCK && com == activity.comList[0]) {
            setBgChoice.backgroundChoicePlayer1(ROCK)
            setBgChoice.backgroundChoiceCom(SCISSORS)
            activity.binding.imgVs.setImageResource(R.drawable.you_win)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })
            showToast(activity, "$name Memilih $ROCK\n COM Memilih $SCISSORS")

        } else if (player == ROCK && com == activity.comList[1]) {
            setBgChoice.backgroundChoicePlayer1(ROCK)
            setBgChoice.backgroundChoiceCom(ROCK)
            activity.binding.imgVs.setImageResource(R.drawable.draw)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulDraw)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $ROCK\n COM Memilih $ROCK")

        } else if (player == ROCK && com == activity.comList[2]) {
            setBgChoice.backgroundChoicePlayer1(ROCK)
            setBgChoice.backgroundChoiceCom(PAPER)
            activity.binding.imgVs.setImageResource(R.drawable.com_win)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $ROCK\n COM Memilih $PAPER")
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    open fun playerChoiceKertas(name: String, player: String, com: String) {
        if (player == PAPER && com == activity.comList[0]) {
            setBgChoice.backgroundChoicePlayer1(PAPER)
            setBgChoice.backgroundChoiceCom(SCISSORS)
            activity.binding.imgVs.setImageResource(R.drawable.com_win)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulOppenentWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $PAPER\n COM Memilih $SCISSORS")

        } else if (player == PAPER && com == activity.comList[1]) {
            setBgChoice.backgroundChoicePlayer1(PAPER)
            setBgChoice.backgroundChoiceCom(ROCK)
            activity.binding.imgVs.setImageResource(R.drawable.you_win)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulWin)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $PAPER\n COM Memilih $ROCK")

        } else if (player == PAPER && com == activity.comList[2]) {
            setBgChoice.backgroundChoicePlayer1(PAPER)
            setBgChoice.backgroundChoiceCom(PAPER)
            activity.binding.imgVs.setImageResource(R.drawable.draw)

            val mainViewModel = ViewModelProvider(activity, activity.defaultViewModelProviderFactory)[MainViewModel::class.java]
            val loginPref = LoginPref(activity)
            mainViewModel.gameBattle(loginPref.getLoginPref().toString(), modeSingle, resulDraw)
            mainViewModel.responseBattle.observe(activity, {
                openDialogResult(activity, it.data?.mode.toString(), name, it.data?.result.toString())
            })

            showToast(activity, "$name Memilih $PAPER\n COM Memilih $PAPER")
        }
    }
}