package com.georgeren.jiankotlin.data.model

/**
 * Created by georgeRen on 2017/12/5.
 */
data class OneIndex(
        var id: String,
        var category: String,
        var display_category: Int,
        var item_id: String,
        var title: String,
        var forward: String,
        var img_url: String,
        var like_count: Int,
        var post_date: String,
        var last_update_date: String,
        var author: Author,
        var video_url: String,
        var audio_url: String,
        var audio_platform: String,
        var start_video: String,
        var volume: String,
        var pic_info: String,
        var words_info: String,
        var subtitle: String,
        var number: Int,
        var serial_id: Int,
        var serial_list: Array<String>,
        var movie_story_id: Int,
        var ad_id: Int,
        var ad_type: Int,
        var ad_pvurl: String,
        var ad_linkurl: String,
        var ad_makettime: String,
        var ad_closetime: String,
        var ad_share_cnt: String,
        var ad_pvurl_vendor: String,
        var content_id: String,
        var content_type: String,
        var content_bgcolor: String,
        var share_url: String,
        var tag_list: Array<Tag>,
        var music_name: String,
        var audio_author: String,
        var audio_album: String)