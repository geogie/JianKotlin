package com.georgeren.jiankotlin.data.net

import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

/**
 * Created by georgeRen on 2017/12/5.
 */
class ServiceFactory {
    companion object {
        fun <T> createRxRetrofitService(clazz: Class<T>, endPoint: String): T {
            return Retrofit
                    .Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .build()
                    .create(clazz)
        }

        fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T {
            return Retrofit
                    .Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .build()
                    .create(clazz)
        }
    }
}