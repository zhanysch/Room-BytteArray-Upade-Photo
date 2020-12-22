package ru.trinitydigital.data.remote

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.trinitydigital.data.local.PrefsHelper
import ru.trinitydigital.data.model.TokenModel


class TokenAuthenticator(private val authApi: AuthApi) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.code == 401) {
            PrefsHelper.saveToken("")
            val result = refreshToken()
            return if (result.isSuccessful && result.body() != null) {
                val token = result.body()?.token ?: ""
                token?.let { PrefsHelper.saveToken(it) }
                response.request.newBuilder()
                    .addHeader("token", token)
                    .build()
            } else {
                null
            }
        }

        return null
    }

    private fun refreshToken(): retrofit2.Response<TokenModel> {
        return runBlocking {
            val map = mapOf(
                "email" to "rojsasha@gmail.com",
                "password" to "fifa11alex"
            )

            return@runBlocking authApi.login(map)
        }
    }
}