package com.carlospinan.maze3d.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException

fun Context.loadBitmap(resource: String): Bitmap {
    val assetManager = this.assets
    try {
        return BitmapFactory.decodeStream(
            assetManager.open(resource)
        )
    } catch (e: IOException) {
        throw Exception("There was an error ${e.localizedMessage}")
    }
}