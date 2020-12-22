package ru.trinitydigital.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.trinitydigital.data.model.TokenModel

interface AuthApi {
    @POST("api/v1/users/auth")
    suspend fun login(@Body data: Map<String, String>): Response<TokenModel>
}