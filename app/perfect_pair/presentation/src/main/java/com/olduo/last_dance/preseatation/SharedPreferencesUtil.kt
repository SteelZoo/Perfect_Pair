package com.olduo.last_dance.preseatation

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesUtil @Inject constructor(
    @ApplicationContext context: Context
) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences("PERFECTPAIR_", Context.MODE_PRIVATE)

    private val USER_ID = "userId"
    private val AUTO_LOGIN_STATE = "autoLogin"

    var userId: String?
        set(value) {
            val editor = preferences.edit()
            editor.putString(USER_ID, value)
            editor.apply()
        }
        get() {
            return preferences.getString(USER_ID, null)
        }

    var autoLoginState: Boolean
        set(value) {
            val editor = preferences.edit()
            editor.putBoolean(AUTO_LOGIN_STATE, value)
            editor.apply()
        }
        get() {
            return preferences.getBoolean(AUTO_LOGIN_STATE, false)
        }

}