package com.example.deliciousnotes.domain.settings.theme_preference.impl

import com.example.deliciousnotes.domain.settings.theme_preference.api.SettingsInteractor
import com.example.deliciousnotes.domain.settings.theme_preference.repository.SettingsRepository

class SettingsInteractorImpl(private val settingsRepository: SettingsRepository) :
    SettingsInteractor {
    @Override
    override fun getTheme(): Boolean {
        return settingsRepository.getTheme()
    }

    @Override
    override fun setTheme(darkTheme: Boolean) {
        settingsRepository.setTheme(darkTheme)
    }
}