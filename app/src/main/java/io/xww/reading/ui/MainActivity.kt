package io.xww.reading.ui

import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.xww.reading.R
import io.xww.reading.adaptor.ViewPager2Adapter
import io.xww.reading.fragment.HomeFragment
import io.xww.reading.fragment.LikeFragment
import io.xww.reading.fragment.MineFragment
import io.xww.reading.fragment.DiscoverFragment


/**
 * @author 项伟伟
 * @lastUpdate 2021年3月3日17点26分
 * */
class MainActivity : AppCompatActivity(){
    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var page : ViewPager2
    val fragments = SparseArray<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        bottomNavigationView = findViewById(R.id.main_navigation)
        page = findViewById(R.id.main_view_pager2)
        fragments.put(0,HomeFragment())
        fragments.put(1,DiscoverFragment())
        fragments.put(2,LikeFragment())
        fragments.put(3,MineFragment())
        page.adapter = ViewPager2Adapter(fragments,this)
        //横向滑动允许，offscreenPageLimit允许加载4个fragment。防止fragment被频繁回收
        page.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        page.offscreenPageLimit = 4
        page.isUserInputEnabled = true

        //设置viewPage2滑动监听
        page.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //改变底部导航栏选中状态
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        //底部导航栏按钮监听
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                //组件 -> page.setCurrentItem（页数，是否平滑滑动）
                R.id.home -> page.setCurrentItem(0,false)
                R.id.search -> page.setCurrentItem(1,false)
                R.id.like -> page.setCurrentItem(2,false)
                R.id.mine -> page.setCurrentItem(3,false)
            }

            true
        }
    }
}