package ru.trinitydigital.cameraimage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.trinitydigital.cameraimage.data.model.ProfileModel

const val DATABASE_NAME = "auth_app"
@Database(entities = [ProfileModel::class],version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun getUserProfileDao(): ProfileDao
}



