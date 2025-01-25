package com.example.deliciousnotes

import android.app.Application
import com.example.deliciousnotes.data.settings.themePreferenceModule
import com.example.deliciousnotes.db.databaseModule
import com.example.deliciousnotes.di.dataModule
import com.example.deliciousnotes.domain.settings.theme_preference.api.SettingsInteractor
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    private val settingsInteractor: SettingsInteractor by inject()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,themePreferenceModule, databaseModule
                )
            )
        }
        settingsInteractor.setTheme(settingsInteractor.getTheme())
    }
}