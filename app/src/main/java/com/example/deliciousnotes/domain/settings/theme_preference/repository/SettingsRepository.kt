package com.example.deliciousnotes.domain.settings.theme_preference.repository

interface SettingsRepository {
    fun getTheme(): Boolean
    fun setTheme(darkTheme: Boolean)
}