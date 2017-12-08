package com.georgeren.jiankotlin.ui.base

import android.support.v4.app.Fragment

/**
 * Created by georgeRen on 2017/12/5.
 */
abstract class BaseFragment: Fragment(){
    abstract fun checkCanDoRefresh(): Boolean
    abstract fun updateData()
}