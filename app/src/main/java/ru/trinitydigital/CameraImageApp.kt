package ru.trinitydigital

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.trinitydigital.data.local.PrefsHelper
import ru.trinitydigital.di.appModules

class CameraImageApp: Application() {
    override fun onCreate() {
        super.onCreate()
        PrefsHelper.init(this)
        startKoin(this, appModules)
    }
}