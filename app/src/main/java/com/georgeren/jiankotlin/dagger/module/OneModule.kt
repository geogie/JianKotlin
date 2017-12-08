package com.georgeren.jiankotlin.dagger.module

import com.georgeren.jiankotlin.data.model.ID
import com.georgeren.jiankotlin.data.model.Index
import com.georgeren.jiankotlin.data.net.OneService
import com.georgeren.jiankotlin.data.net.ServiceFactory
import dagger.Module
import dagger.Provides
import rx.Observable
import javax.inject.Singleton

/**
 * Created by georgeRen on 2017/12/7.
 */
@Singleton
@Module
class OneModule {
    private val endPoint = "http://v3.wufazhuce.com:8000"
    var id: Int = 0
    constructor(id: Int){
        this.id = id
    }

    constructor()

    @Singleton
    @Provides
    fun provideOne(): Observable<Index>{
        return ServiceFactory.Companion
                .createRxRetrofitService(OneService::class.java, endPoint)
                .getIndexList(id)
    }

    @Singleton
    @Provides
    fun provideIdList(): Observable<ID>{
        return ServiceFactory.Companion
                .createRxRetrofitService(OneService::class.java, endPoint)
                .getIdList()
    }
}