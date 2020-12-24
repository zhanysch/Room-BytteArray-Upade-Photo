package ru.trinitydigital.cameraimage.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import ru.trinitydigital.cameraimage.data.model.AuthModel
import ru.trinitydigital.cameraimage.data.model.ProfileModel
import ru.trinitydigital.cameraimage.data.model.TokenModel

interface RetrofitService {

    @POST("api/v1/users/auth")
    suspend fun login(@Body data: Map<String, String>): Response<TokenModel>

    @POST("api/v1/users/auth")
    suspend fun loginDataClass(@Body data: AuthModel): Response<TokenModel>

    @GET("api/v1/users/profile")
    suspend fun loadUserProfile(): Response<ProfileModel?>

    @Multipart  // если отправляютс какие либо данные то он автоматич становитс Multipart
    @PUT("api/v1/users/profile")  /// для обновления данных пользователя
    suspend fun updateUserWithImage(
        @Part("body")body: RequestBody,  // это как json
        @Part avatar: MultipartBody.Part    // эта картинка
    ): Response<ProfileModel?>
}