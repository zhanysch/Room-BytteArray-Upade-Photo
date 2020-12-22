package ru.trinitydigital.cameraimage.ui.main

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import com.google.android.material.shape.CornerFamily
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

        val radius = resources.getDimension(R.dimen.imageRadius)
        image.shapeAppearanceModel = image.shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)
            .build()

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