package io.xww.reading.servers

import android.graphics.Bitmap
import java.io.File

interface ImageInterface {

    /**
     * 从URL获取图片
     * @param url
     */
    fun getImgFromUrl(url: String): Bitmap?

    /**
     * 从文件获取图片
     * @param file
     * @param quality
     */
    fun getImgFromFile(file: File,quality: Int): Bitmap?

    /**
     * 保存bitmap文件
     * @param bitmap
     * @param file
     * @param quality in 0..100
     */
    fun saveBitmapFile(bitmap: Bitmap, file: File, quality: Int)

}