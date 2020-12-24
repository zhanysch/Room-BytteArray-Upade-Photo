package ru.trinitydigital.cameraimage.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.trinitydigital.cameraimage.ui.main.MainViewModel
import java.io.File


fun File.toImageRequestBody(name: String): MultipartBody.Part {
        val image = this.asRequestBody("image/*".toMediaTypeOrNull()).let {
            MultipartBody.Part.createFormData(
                name,                     // MainViewModel.AVATAR,
                this.name,
                it
            )
        }
        return image
}

fun Any.toJsonRequestBody(): RequestBody {
    val body = Gson().toJson(this)
        .toRequestBody("application/json".toMediaTypeOrNull())
    return body

}



