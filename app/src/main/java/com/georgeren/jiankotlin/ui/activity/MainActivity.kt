package com.georgeren.jiankotlin.ui.activity

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.georgeren.jiankotlin.R
import com.georgeren.jiankotlin.ui.adapter.ViewPagerAdapter
import com.georgeren.jiankotlin.ui.fragment.OneFragment
import com.georgeren.jiankotlin.ui.fragment.StoryFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * lateinit：延迟初始化
 */
class MainActivity : BaseOriginalActivity() {
    private val FIRST_PAGE_INDEX: Int = 0

    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun setupActivityComponent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        initViewPager()
        initPtr()
    }

    private fun initViewPager() {
        viewPager.adapter = mViewPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mViewPagerAdapter.switchTo(position)
            }
        })

        smartTabLayout.setViewPager(viewPager)
    }

    private fun initAdapter() {
        //Create a collection object using arrayOf
        val fragmentList = arrayOf(StoryFragment(), OneFragment())
        val titleList = arrayOf("知乎日报", "一个")

        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        mViewPagerAdapter.switchTo(FIRST_PAGE_INDEX)
    }

    private fun initPtr() {
        store_house_ptr_frame.disableWhenHorizontalMove(true)
        store_house_ptr_frame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mViewPagerAdapter.updateData()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?):
                    Boolean = mViewPagerAdapter.checkCanDoRefresh()
        })
    }

    fun refreshComplete() {
        store_house_ptr_frame.refreshComplete()
    }

}
