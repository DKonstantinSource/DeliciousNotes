package com.example.deliciousnotes.domain.settings.theme_preference.api

interface SettingsInteractor {
    fun getTheme(): Boolean
    fun setTheme(darkTheme: Boolean)
}