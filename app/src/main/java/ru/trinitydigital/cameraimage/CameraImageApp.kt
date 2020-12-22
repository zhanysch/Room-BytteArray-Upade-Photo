package ru.trinitydigital.cameraimage

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.trinitydigital.cameraimage.data.local.PrefsHelper
import ru.trinitydigital.cameraimage.di.appModules

class CameraImageApp: Application() {
    override fun onCreate() {
        super.onCreate()
        PrefsHelper.init(this)
        startKoin(this, appModules)
    }
}