package com.georgeren.jiankotlin.data.model

/**
 * Created by georgeRen on 2017/12/5.
 */
data class StoryDetail(var body: String,
                       var image_source: String,
                       var title: String,
                       var image: String,
                       var share_url: String,
                       var js: List<String>,
                       var ga_prefix: String,
                       var images: List<String>,
                       var type: Int,
                       var id: Int,
                       var css: List<String>) {
}