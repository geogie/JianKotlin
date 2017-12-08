package com.georgeren.jiankotlin.dagger.module

import com.georgeren.jiankotlin.data.api.ZhiHuRiBaoService
import com.georgeren.jiankotlin.data.model.StoryDetail
import com.georgeren.jiankotlin.data.net.ServiceFactory
import com.georgeren.jiankotlin.ui.model.RiBao
import dagger.Module
import dagger.Provides
import rx.Observable
import javax.inject.Singleton

/**
 * Created by georgeRen on 2017/12/5.
 */
@Singleton
@Module
class StoryModule {
    private val endPoint = "http://news-at.zhihu.com"
//    val endPoint2 = "http://news.at.zhihu.com"

    var storyId: Int = 0
    var date: String = ""

    constructor()

    constructor(date: String) {
        this.date = date
    }

    @Singleton
    @Provides
    fun provideStories(): Observable<RiBao> {
        return ServiceFactory.Companion
                .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
                .getLatestRiBao(date)
    }

    @Singleton
    @Provides
    fun provideStoryDetail(): Observable<StoryDetail> {
        return ServiceFactory.Companion
                .createRxRetrofitService(ZhiHuRiBaoService::class.java, endPoint)
                .getStoryDetailById(storyId)
    }
}