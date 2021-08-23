package io.xww.reading.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.xww.reading.R
import io.xww.reading.data.Box
import io.xww.reading.helper.ImageHelper

class ContentActivity : AppCompatActivity() ,View.OnTouchListener , ScaleGestureDetector.OnScaleGestureListener{
    private val image = ImageHelper(this)
    private var imageView: ImageView? = null
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var box: Box =Box("", "", "")

    /**
     * 伴生类
     */
    companion object{
        fun startInstance(context: Context, data: Box){
            val appContext = context.applicationContext
            val intent = Intent(appContext, ContentActivity::class.java)
            intent.putExtra("data", data)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            appContext.startActivity(intent)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initData() {
        box = intent.getSerializableExtra("data") as Box
        imageView = findViewById(R.id.action_context_img)
        imageView!!.setOnTouchListener(this)
        image.setImageViewByUrl(imageView!!, box.img)
        scaleGestureDetector = ScaleGestureDetector(this, this)
    }

    private fun getScreenwidth(): Int {
        val metrics = resources.displayMetrics
        return metrics.widthPixels
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        initData()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(event)
        return true
    }



    private var scale = 1.0f
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        val scaleFactor = detector?.scaleFactor
        if (scaleFactor != null){
            val matrix = android.graphics.Matrix()
            val t = scaleFactor * scale
            matrix.postScale(t,t,detector.focusX,detector.focusY)
        }

        return false
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
    }


}