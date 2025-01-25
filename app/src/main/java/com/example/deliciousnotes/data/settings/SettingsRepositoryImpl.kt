package com.example.deliciousnotes.data.settings

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.deliciousnotes.Constants.Companion.KEY_SWITCH_THEME
import com.example.deliciousnotes.domain.settings.theme_preference.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {

    override fun getTheme(): Boolean {

        val isSystemDarkMode =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        if (isSystemDarkMode) {
            return true
        }

        return if (sharedPreferences.contains(KEY_SWITCH_THEME)) {
            sharedPreferences.getBoolean(KEY_SWITCH_THEME, false)
        } else {
            false
        }
    }

    override fun setTheme(darkTheme: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (darkTheme) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )

        sharedPreferences.edit()
            .putBoolean(KEY_SWITCH_THEME, darkTheme)
            .apply()
    }
}