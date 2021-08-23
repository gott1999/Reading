package io.xww.reading.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.xww.reading.R
import io.xww.reading.adaptor.DiscoverRecyclerAdapter
import io.xww.reading.servers.objects.InitData

class DiscoverFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener{
    private var isInit = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onResume(){
        super.onResume()
        //判断是否需要初始化
        if (!isInit){
            recyclerView = requireView().findViewById(R.id.fragment_search_recyclerView)
            swipeRefreshLayout = requireView().findViewById(R.id.fragment_discover_refresh)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
            recyclerView.apply { adapter =  DiscoverRecyclerAdapter(context, InitData.initDataB()) }
            isInit = true
        }
        swipeRefreshLayout.setOnRefreshListener(this)
    }
    override fun onRefresh() {
        Handler(Looper.getMainLooper()).postDelayed({
            swipeRefreshLayout.isRefreshing = false
            recyclerView.apply { adapter =  DiscoverRecyclerAdapter(context, InitData.initDataB()) }
        },1000)
    }
}