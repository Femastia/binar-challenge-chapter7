package com.ihsan.binarchallengechapter7.helper

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class LoginPref(context: Context) {
    private val sharedPrefFile = "tokenlogin"
    companion object {
        const val LOGIN_KEY = "login_key"
        const val TAG = "LoginPref"
    }

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

    fun setLoginPref(token: String){
        Log.i(TAG, "setLoginPref: token = $token")
        val prefEditor = sharedPreferences.edit()
        prefEditor.putString(LOGIN_KEY, token)
        prefEditor.apply()
    }

    fun getLoginPref(): String? {
        Log.i(TAG, "getLoginPref...")
        return sharedPreferences.getString(LOGIN_KEY, "tokenNull")
    }

    fun clearStatusLogin() {
        Log.i(TAG, "clearStatusLogin...")
        val prefEditor = sharedPreferences.edit()
        prefEditor.clear()
        prefEditor.apply()
    }
}