package ru.trinitydigital.cameraimage.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.trinitydigital.cameraimage.data.model.ProfileModel
import ru.trinitydigital.cameraimage.data.repositories.UserRepository
import ru.trinitydigital.cameraimage.utils.toImageRequestBody
import ru.trinitydigital.cameraimage.utils.toJsonRequestBody
import java.io.File

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    val data = MutableLiveData<ProfileModel>()
    val error = MutableLiveData<String>()

     fun authUser() {
        viewModelScope.launch {
            runCatching {
                val result = repository.login("rojsasha@gmail.com", "fifa11alex")
                if (result.isSuccessful)
                    loadUser()
            }.onFailure {
                it.message?.let { it1 -> Log.e("error", it1) }
            }
        }
    }


    private fun loadUser() {
        viewModelScope.launch{
            runCatching {
                val result = repository.loadUserProfile()
                data.postValue(result.body())

            }.onFailure {
                Log.d("fsfsdf","fsdfs")
                error.postValue(it.localizedMessage)

            }
        }
    }

    fun updateUserWithPhoto(file: File) {   //для обновления фото профиля метод
        viewModelScope.launch {
            kotlin.runCatching {
                val result = data.value?.toJsonRequestBody()?.let {
                    repository.updateUserWithImage(body = it,avatar = file.toImageRequestBody(AVATAR))
                }
                if (result != null) {
                    if (result.isSuccessful)
                        data.postValue(result.body())
                }
                Log.d("fsfsdf","fsdfs")
            }.onFailure {
                Log.d("fsfsdf","fsdfs")

            }
        }
    }

    companion object{
        private const val AVATAR = "avatar"
    }
}