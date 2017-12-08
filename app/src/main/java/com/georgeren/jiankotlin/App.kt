package com.georgeren.jiankotlin

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by georgeRen on 2017/12/5.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}