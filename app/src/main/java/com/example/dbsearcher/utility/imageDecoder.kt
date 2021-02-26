package com.example.dbsearcher.utility

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

    fun imageDecoder(imageCode: String?): Bitmap {
        var imageBytes: ByteArray
        if (imageCode?.get(0) == '/') {
            imageBytes = Base64.decode(imageCode, Base64.DEFAULT)
        } else {
            var doubleDecodedIMG = Base64.decode(imageCode, Base64.DEFAULT)
            imageBytes = Base64.decode(doubleDecodedIMG, Base64.DEFAULT)
        }
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }