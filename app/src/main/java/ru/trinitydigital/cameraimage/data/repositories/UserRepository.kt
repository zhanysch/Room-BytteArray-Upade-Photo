package ru.trinitydigital.cameraimage.data.repositories

import androidx.lifecycle.LiveData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import ru.trinitydigital.cameraimage.data.interactors.UserInteractor
import ru.trinitydigital.cameraimage.data.local.AppDataBase
import ru.trinitydigital.cameraimage.data.local.PrefsHelper
import ru.trinitydigital.cameraimage.data.model.ProfileModel
import ru.trinitydigital.cameraimage.data.model.TokenModel

interface UserRepository {
    suspend fun login(userName: String, password: String): Response<TokenModel>
    suspend fun loadUserProfile(): Response<ProfileModel?>
    suspend fun updateUserWithImage(body: RequestBody, avatar: MultipartBody.Part): Response<ProfileModel?>  // запрос на обновление данных
    fun updateUser(user: ProfileModel)   // для обновлен данных пo room
    fun getUser(id : Int): LiveData<ProfileModel?>   //  room

}

class UserRepositoryImpl(private val network: UserInteractor, private val db: AppDataBase) : UserRepository {

    override suspend fun login(userName: String, password: String): Response<TokenModel> {
        val result = network.login(userName, password)
        result.body()?.let { PrefsHelper.saveToken(it.token) }  //сохранен токена
        return result
    }

    override suspend fun loadUserProfile(): Response<ProfileModel?> {
        val result = network.loadUserProfile()
        result.body()?.let { db.getUserProfileDao().saveProfilie(it) }

        return result
    }

    override suspend fun updateUserWithImage(
        body: RequestBody,
        avatar: MultipartBody.Part
    ): Response<ProfileModel?> {
        return network.updateUserWithImage(body=body,avatar=avatar)
    }

    override fun updateUser(user: ProfileModel) {  // полно обновление данных по room дата класса ProfileModel
        db.getUserProfileDao().upadateProfile(user)
    }

    override fun getUser(id: Int): LiveData<ProfileModel?> {
        return db.getUserProfileDao().getProfile(id)
    }
}
