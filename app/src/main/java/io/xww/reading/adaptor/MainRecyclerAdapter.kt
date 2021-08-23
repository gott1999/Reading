package io.xww.reading.adaptor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.xww.reading.R
import io.xww.reading.data.Box
import io.xww.reading.helper.ImageHelper
import io.xww.reading.ui.ContentActivity
import io.xww.reading.ui.MainActivity


/**
 * 加载一个图片和一段文本
 */
class MainRecyclerAdapter(context: Context, list: ArrayList<Box>): RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerHolder>(){
    private val mContext = context
    private val data = list
    private val imageHelper = ImageHelper(mContext)

    class MainRecyclerHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img: ImageView = itemView.findViewById(R.id.fragment_home_model1_img)
        val text: TextView = itemView.findViewById(R.id.fragment_home_model1_text)

    }

    override fun getItemCount(): Int { return data.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        return MainRecyclerHolder(View.inflate(mContext, R.layout.fragment_home_model1, null))
    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        val data: Box = data[position]
        holder.text.text = data.text
        imageHelper.setImageViewByUrl(holder.img,data.img)
        holder.itemView.setOnClickListener{ ContentActivity.startInstance(mContext,data) }
    }




}