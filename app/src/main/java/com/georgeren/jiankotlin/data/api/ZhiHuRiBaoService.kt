package com.georgeren.jiankotlin.data.api

import com.georgeren.jiankotlin.data.model.StoryDetail
import com.georgeren.jiankotlin.ui.model.RiBao
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by georgeRen on 2017/12/5.
 */
interface ZhiHuRiBaoService {
    @GET("/api/4/news/before/{dateTime}")
    fun getLatestRiBao(@Path("dateTime") dateTime: String): Observable<RiBao>

    @GET("/api/4/news/{id}")
    fun getStoryDetailById(@Path("id") id: Int): Observable<StoryDetail>
}