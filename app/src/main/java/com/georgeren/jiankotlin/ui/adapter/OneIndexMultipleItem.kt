package com.georgeren.jiankotlin.ui.adapter

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.georgeren.jiankotlin.data.model.OneIndex
import com.georgeren.jiankotlin.data.model.Weather

/**
 * Created by georgeRen on 2017/12/5.
 */
class OneIndexMultipleItem(private val itemType: Int,
                           val indexData: OneIndex? = null,
                           val weather: Weather? = null) : MultiItemEntity {
    companion object {
        val BLANK = -1
        val WEATHER = 0
        val TOP = 1
        val READ = 2
    }

    override fun getItemType(): Int = itemType
}