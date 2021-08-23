package io.xww.reading.servers.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import io.xww.reading.servers.ImageInterface
import io.xww.reading.servers.objects.Changer
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageService: ImageInterface {
    private val urlSet: Map<String, String> = HashMap()
    private val urlList: List<String> = ArrayList()
    private val service: ExecutorService = Executors.newFixedThreadPool(3)


    override fun getImgFromUrl(url: String): Bitmap? {
        val connection = URL(url).openConnection()
        connection.connectTimeout = 10000
        connection.readTimeout = 20000
        val byte = connection.getInputStream()
        return BitmapFactory.decodeStream(byte)
    }

    override fun getImgFromFile(file: File, quality: Int): Bitmap? {
        return BitmapFactory.decodeFile(file.path, Changer.setQuality(quality))
    }


    override fun saveBitmapFile(bitmap: Bitmap, file: File, quality: Int) {
        try {
            val outputStream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream)
            outputStream.flush()
            outputStream.close()
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

}