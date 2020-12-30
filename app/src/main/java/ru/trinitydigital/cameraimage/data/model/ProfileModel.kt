package ru.trinitydigital.cameraimage.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity  // room
data class ProfileModel(
    @PrimaryKey  // room
    val id: Int,
    val email: String?,
    @SerializedName("role_code")
    val roleCode: String?,
    var phone: String?,
    @SerializedName("status_code")
    val statusCode: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("last_name")
    var lastName: String?,
    var birthdate: String?,
    var gender: String?,
    @SerializedName("about_me")
    var aboutMe: String,
    @SerializedName("instagram_link")
    var instagramLink: String?,
    @SerializedName("vk_link")
    var vkLink: String?,
    var avatar: String?, //@ColumnInfo тип ячейки
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)  // BLOB хранитс тип данных в байтах
    var image : ByteArray? = null

) : Parcelable