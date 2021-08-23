package io.xww.reading.servers.objects

import android.graphics.BitmapFactory

object Changer {

    /**
     * 把url从最后一个“/”开始裁剪
     */
    fun subUrl(url: String): String{ return url.substring(url.lastIndexOf("/")) }

    /**
     * 设置图片质量
     * @param quality in 0..100
     */
    fun setQuality(quality: Int): BitmapFactory.Options{
        require(!(quality < 0 || quality > 100)) { "quality must be 0..100" }
        val factoryOption : BitmapFactory.Options = BitmapFactory.Options()
        factoryOption.inJustDecodeBounds = true
        factoryOption.inSampleSize = 100/quality
        factoryOption.inJustDecodeBounds = false
        return factoryOption
    }

    /**
     * 裁剪图片
     */
    fun subImage(){

    }

    /**
     * 缩放图片
     */
    fun changeImage(){

    }

}