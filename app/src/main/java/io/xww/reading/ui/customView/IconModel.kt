package io.xww.reading.ui.customView

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.xww.reading.R

class IconModel : LinearLayout {
    private lateinit var imageView : ImageView
    private lateinit var textView : TextView

    constructor(context: Context?) : super(context)
    @SuppressLint("Recycle")
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        LayoutInflater.from(context).inflate(R.layout.fragment_mine_model1,this,true)
        textView = findViewById(R.id.fragment_mine_model1_text)
        imageView = findViewById(R.id.fragment_mine_model1_img)
        val typedArray : TypedArray = context!!.obtainStyledAttributes(attrs,R.styleable.IconModel)

        val textId = typedArray.getString(R.styleable.IconModel_text)
        textView.text = textId

        val iconId = typedArray.getResourceId(R.styleable.IconModel_icon,R.drawable.ic_add)
        imageView.setImageResource(iconId)
        //typedArray回收
        typedArray.recycle()



    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
}