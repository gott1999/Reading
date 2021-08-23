package io.xww.reading.adaptor

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.xww.reading.R
import io.xww.reading.data.Box
import io.xww.reading.helper.ImageHelper
import io.xww.reading.ui.ContentActivity

/**
 * 加载一个带标题，随意图片，一个文本
 */
class DiscoverRecyclerAdapter(context: Context, list: ArrayList<Box>): RecyclerView.Adapter<DiscoverRecyclerAdapter.DiscoverViewHolder>() {
    private val mContext = context
    private val data = list
    private val imageHelper = ImageHelper(mContext)

    class DiscoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // 尽量选择自定义布局，减少View的嵌套
        val title: TextView = itemView.findViewById(R.id.fragment_discover_model1_title)
        val img: ImageView = itemView.findViewById(R.id.fragment_discover_model1_img)
        val text: TextView = itemView.findViewById(R.id.fragment_discover_model1_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
       return DiscoverViewHolder(View.inflate(mContext, R.layout.fragment_discover_model1, null))
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val data: Box = data[position]
        holder.title.text = data.title
        holder.text.text = data.text
        imageHelper.setImageViewByUrl(holder.img,data.img)
        holder.itemView.setOnClickListener{ ContentActivity.startInstance(mContext,data) }
    }

    override fun getItemCount(): Int { return data.size }

}