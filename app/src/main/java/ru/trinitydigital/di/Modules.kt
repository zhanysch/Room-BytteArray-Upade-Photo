package ru.trinitydigital.di

import org.koin.dsl.module.module
import ru.trinitydigital.cameraimage.BuildConfig.BASE_URL
import ru.trinitydigital.data.remote.RetrofitBuilder

val viewModelModule = module {
}

val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) }

}

val appModules = listOf(viewModelModule, repositoryModule)