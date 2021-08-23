package io.xww.reading.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.xww.reading.R
import io.xww.reading.adaptor.MainRecyclerAdapter
import io.xww.reading.servers.objects.InitData

class HomeFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener{
    private var isInit = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //懒加载nb
    override fun onResume() {
        super.onResume()
        if (!isInit){
            recyclerView = requireView().findViewById(R.id.fragment_home_view)
            swipeRefreshLayout = requireView().findViewById(R.id.fragment_home_refresh)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.apply { adapter =  MainRecyclerAdapter(context, InitData.initData()) }
            isInit = true
        }
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        Handler(Looper.getMainLooper()).postDelayed({
            swipeRefreshLayout.isRefreshing = false
            recyclerView.apply { adapter =  MainRecyclerAdapter(context, InitData.initData()) }
        },1000)
    }



}