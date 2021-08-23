package io.xww.reading.helper

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import io.xww.reading.servers.impl.ImageService
import io.xww.reading.servers.objects.Changer
import java.io.File
import java.lang.Exception
import java.lang.ref.SoftReference
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.HashMap

class ImageHelper(context: Context) {
    /**
     * 上下文需要传递
     */
    private val mContext :Context = context

    /**
     * 设置线程池大小
     */
    private val server : ExecutorService = Executors.newFixedThreadPool(1)

    /**
     * 可变Map，存储软缓存（Bitmap）
     */
    private val bitmapMap: MutableMap<String, SoftReference<Bitmap>> = HashMap()

    /**
     * 堆栈，存储软缓存（ImageView）
     */
    private val stack: Stack<SoftReference<ImageView>> = Stack()

    /**
     * 服务类
     */
    private val imageService = ImageService()

    /**
     * 设置绑定图片和url
     * @param it ImageView
     * @param url 图片地址
     */
    fun setImageViewByUrl(it: ImageView,url: String){
        val handler = object: Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val imageView = stack.pop()!!.get()!!
                imageView.setImageBitmap(msg.data.getParcelable("bitmap"))
            }
        }
        it.tag = url
        stack.push(SoftReference(it))
        showMeImage(url,handler)
    }

    /**
     * 获取图片
     */
    private fun showMeImage(url: String, handler: Handler){
        server.execute{
            try {
                val bitmap: Bitmap? = checkLocal(url)
                require(bitmap != null){"Network error"}
                // 发送图片给MessageQueue
                val message = Message.obtain()
                message.what = 1
                val bundle = Bundle()
                bundle.putParcelable("bitmap",bitmap)
                message.data = bundle
                handler.sendMessage(message)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    /**
     * 检查图片(保存依据局部性原则)
     * 1.内存中查找  T:返回
     * 2.缓存中查找  T:写入内存
     * 3.URL查找    T:写入缓存
     * 4.找不到
     * @param url 图片地址
     */
    private fun checkLocal(url: String): Bitmap?{
        var bitmap: Bitmap?
        val key = Changer.subUrl(url)
        val file = File(mContext.cacheDir,key)
        synchronized(bitmapMap){
            if (bitmapMap.containsKey(key)){
                bitmap = bitmapMap[key]!!.get()
            }else if (file.exists()) {
                bitmap = imageService.getImgFromFile(file, 100)
                bitmapMap[key] = SoftReference(bitmap)
            }else {
                bitmap = imageService.getImgFromUrl(url)
                if (bitmap != null) {
                    imageService.saveBitmapFile(bitmap!!, file, 100)
                }else {
                    bitmap = null
                }
            }
        }
        return bitmap
    }


}