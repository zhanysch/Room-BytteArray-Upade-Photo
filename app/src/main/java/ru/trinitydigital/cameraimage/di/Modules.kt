package ru.trinitydigital.cameraimage.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.trinitydigital.cameraimage.BuildConfig.BASE_URL
import ru.trinitydigital.cameraimage.data.interactors.UserInteractor
import ru.trinitydigital.cameraimage.data.interactors.UserInteractorImpl
import ru.trinitydigital.cameraimage.data.local.AppDataBase
import ru.trinitydigital.cameraimage.data.remote.RetrofitBuilder
import ru.trinitydigital.cameraimage.data.repositories.UserRepository
import ru.trinitydigital.cameraimage.data.repositories.UserRepositoryImpl
import ru.trinitydigital.cameraimage.ui.main.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) }
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java,  // для room
        "db_name")
            .allowMainThreadQueries()
            .build()
    }
    single<UserInteractor> { UserInteractorImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(),get()) }  // второй get для Room, т.к сохранен Room в class repository

}

val appModules = listOf(viewModelModule, repositoryModule)