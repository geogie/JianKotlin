package com.georgeren.jiankotlin.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.georgeren.jiankotlin.ui.base.BaseFragment


/**
 * Created by georgeRen on 2017/12/5.
 */
class ViewPagerAdapter(fm: FragmentManager,
                       private val mFragmentList: Array<BaseFragment>,
                       private val titleList: Array<String>) : FragmentStatePagerAdapter(fm) {

    var mCurrentFragment: BaseFragment? = null

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    fun updateData() {
        if (mCurrentFragment == null) return

        return mCurrentFragment!!.updateData()
    }

    fun checkCanDoRefresh(): Boolean {
        if (mCurrentFragment == null) {
            return true
        }
        return mCurrentFragment!!.checkCanDoRefresh()
    }

    fun switchTo(position: Int) {
        mCurrentFragment = mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence = titleList[position]

}