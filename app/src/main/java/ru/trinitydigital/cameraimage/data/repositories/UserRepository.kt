package ru.trinitydigital.cameraimage.data.repositories

import retrofit2.Response
import ru.trinitydigital.cameraimage.data.interactors.UserInteractor
import ru.trinitydigital.cameraimage.data.model.TokenModel

interface UserRepository {
    suspend fun login(userName: String, password: String): Response<TokenModel>
}

class UserRepositoryImpl(private val network: UserInteractor) : UserRepository {

    override suspend fun login(userName: String, password: String) =
        network.login(userName, password)
}