package ru.trinitydigital.cameraimage.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.trinitydigital.cameraimage.BuildConfig.BASE_URL
import ru.trinitydigital.cameraimage.data.remote.RetrofitBuilder
import ru.trinitydigital.cameraimage.ui.main.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel() }
}

val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) }

}

val appModules = listOf(viewModelModule, repositoryModule)