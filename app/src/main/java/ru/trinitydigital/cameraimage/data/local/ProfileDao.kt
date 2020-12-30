package ru.trinitydigital.cameraimage.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.trinitydigital.cameraimage.data.model.ProfileModel

@Dao  //class для запроса в Room
interface ProfileDao {

     // сохран данные
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun saveProfilie(user :ProfileModel)

    @Update // обновл данные
    fun upadateProfile(user :ProfileModel)

    @Query("SELECT * FROM ProfileModel WHERE :id = id") // обновл данные
    fun getProfile(id :Int) : LiveData <ProfileModel?> //ProfileModel? -> ? возможно null
}