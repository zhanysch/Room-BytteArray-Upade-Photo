package ru.trinitydigital.cameraimage.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.trinitydigital.cameraimage.R
import ru.trinitydigital.cameraimage.common.BaseUserPhotoActivity
import ru.trinitydigital.cameraimage.common.pickPhotoFromGalleryWithPermissionCheck
import ru.trinitydigital.cameraimage.common.shootPhotoWithPermissionCheck
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception

class MainActivity : BaseUserPhotoActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()

        viewModel.authUser()

        setupViewModel()

        val radius = resources.getDimension(R.dimen.imageRadius)
        image.shapeAppearanceModel = image.shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)
            .build()

    }

    private fun setupViewModel() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_LONG).show()
        })

        // выкачиваетс данные с БД
        viewModel.data.observe(this, Observer {
            viewModel.getUserFromDB(it.id).observe(this, Observer {
                val bmp =
                    it?.image?.size?.let { it1 ->
                        BitmapFactory.decodeByteArray(it?.image, 0 ,
                            it1
                        )
                    }
                imageDb.setImageBitmap(bmp)
            })

            // выкачиваетс данные с интернета
          Picasso.get().load(it.avatar).into(image, object : Callback{
              override fun onSuccess() {
                 val imageDrawble = (image.drawable as BitmapDrawable).bitmap
                  val stream = ByteArrayOutputStream()
                  imageDrawble.compress(Bitmap.CompressFormat.PNG,90,stream)   // для преобразования отправленной картинки
                  // c png в bytearray в png формат
                  val array = stream.toByteArray()  // получаем в byte array картинку
                  viewModel.updateUser(array)
                  Log.d("Ffsfsddf","fsfsdf")
              }
              override fun onError(e: Exception?) {
                  Log.d("Ffsfsddf","fsfsdf")
              }

          })
        })
    }

    private fun setupListeners() {
        camera.setOnClickListener {
            shootPhotoWithPermissionCheck()
        }

        gallery.setOnClickListener {
            pickPhotoFromGalleryWithPermissionCheck()
        }
    }

    override fun showPhoto(file: File) {
        /*val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        image.setImageBitmap(bitmap)*/
        viewModel.updateUserWithPhoto(file)
    }

    override fun showPhoto1(file: Uri?) {
    }

    //минута 2:00
}