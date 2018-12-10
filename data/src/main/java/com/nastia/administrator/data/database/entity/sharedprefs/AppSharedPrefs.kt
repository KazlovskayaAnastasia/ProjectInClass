package com.nastia.administrator.data.database.entity.sharedprefs

import android.content.Context
import android.content.SharedPreferences

class AppSharedPrefs(val context: Context) {

    private val sharedPrefs : SharedPreferences
    private var token: String = ""

    companion object {
        private const val SHARED_PREFS_NAME = "nsknckds"
        private const val KEY_TOKEN = "bsjdnckfsj"
    }

    init{
        sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun putToken(token:String){
        this.token = token
        sharedPrefs.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken():String {
        if(token.isEmpty()){
            token = sharedPrefs.getString(KEY_TOKEN, "") ?: ""
        }
        return token
    }

}