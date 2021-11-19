package com.ihsan.binarchallengechapter7.helper

import android.content.Context
import android.content.SharedPreferences

class LoginPref(context: Context) {
    companion object {
        const val SP_NAME = "login_pref"
        const val IS_LOGIN = "is_login"
    }

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    fun setLoginPref(){
        val prefEditor = sharedPreferences.edit()
        prefEditor.putBoolean(IS_LOGIN, true)
        prefEditor.apply()
    }

    fun getLoginPref():Boolean {
        return true
    }

    fun clearStatusLogin() {
        val prefEditor = sharedPreferences.edit()
        prefEditor.clear()
        prefEditor.apply()
    }
}