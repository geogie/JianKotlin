package com.georgeren.jiankotlin.data.net

import com.georgeren.jiankotlin.data.model.ID
import com.georgeren.jiankotlin.data.model.Index
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by georgeRen on 2017/12/7.
 */
interface OneService {
    @GET("/api/onelist/{id}/0?cchannel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    fun getIndexList(@Path("id") id: Int): Observable<Index>

    @GET("/api/onelist/idlist/?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    fun getIdList(): Observable<ID>
}