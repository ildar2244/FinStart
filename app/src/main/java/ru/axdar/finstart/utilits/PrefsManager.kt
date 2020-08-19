package ru.axdar.finstart.utilits

import android.content.Context
import android.content.SharedPreferences

object PrefsManager {

    private const val PREF_KEY_ACCESS_TOKEN = "FinStart_Prefs_Token"
    private const val PREF_KEY_USER_LOGIN_STATUS = "FinStart_User_Login_Status"
    private lateinit var preference: SharedPreferences

    fun init(context: Context) {
        preference = context.getSharedPreferences(
            PREF_KEY_ACCESS_TOKEN, Context.MODE_PRIVATE)
    }

    fun saveAuthState(state: Boolean) {
        putValue(PREF_KEY_USER_LOGIN_STATUS to state)
    }

    fun getAuthState(): Boolean = preference.getBoolean(
        PREF_KEY_USER_LOGIN_STATUS, false)

    private fun putValue(pair: Pair<String, Any>) = with(preference.edit()) {
        val key = pair.first
        val value = pair.second
        when(value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in Shared Preferences")
        }
        apply()
    }
}