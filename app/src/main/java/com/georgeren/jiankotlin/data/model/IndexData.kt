package com.georgeren.jiankotlin.data.model

/**
 * Created by georgeRen on 2017/12/5.
 */
data class IndexData(var id: String,
                     var date: String,
                     var weather: Weather,
                     var content_list: Array<OneIndex>)