package com.georgeren.jiankotlin.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by georgeRen on 2017/12/6.
 * 扩展
 */
/**
 * 扩展inflate
 */
fun ViewGroup.inflate(layoutId: Int): View =
        LayoutInflater.from(context).inflate(layoutId, this, false)