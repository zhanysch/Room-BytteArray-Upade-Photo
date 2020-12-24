package ru.trinitydigital.cameraimage.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.trinitydigital.cameraimage.data.model.ProfileModel
import ru.trinitydigital.cameraimage.data.model.TokenModel
import ru.trinitydigital.cameraimage.data.repositories.UserRepository

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
}