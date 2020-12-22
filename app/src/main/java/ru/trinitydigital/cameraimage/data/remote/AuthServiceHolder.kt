package ru.trinitydigital.cameraimage.data.remote

class AuthServiceHolder {

    private var apiService: RetrofitService? = null

    fun getApiService(): RetrofitService? {
        return apiService
    }

    fun setApiService(apiService: RetrofitService?) {
        this.apiService = apiService
    }
}