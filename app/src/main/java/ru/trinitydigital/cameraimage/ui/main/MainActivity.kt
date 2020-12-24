package ru.trinitydigital.cameraimage.ui.main

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.trinitydigital.cameraimage.R
import ru.trinitydigital.cameraimage.common.BaseUserPhotoActivity
import ru.trinitydigital.cameraimage.common.pickPhotoFromGalleryWithPermissionCheck
import ru.trinitydigital.cameraimage.common.shootPhotoWithPermissionCheck
import java.io.File

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
        viewModel.data.observe(this, Observer {
          Picasso.get().load(it.avatar).into(image)
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
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        image.setImageBitmap(bitmap)
    }

    override fun showPhoto1(file: Uri?) {
    }
}