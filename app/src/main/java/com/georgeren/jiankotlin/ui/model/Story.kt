package com.georgeren.jiankotlin.ui.model

/**
 * Created by georgeRen on 2017/12/5.
 * data：只持有数据的bean类
 */
data class Story(var images: List<String>,
                 var type: Int,
                 var id: Int,
                 var ga_prefix: String,
                 var title: String,
                 var date: String) {
}