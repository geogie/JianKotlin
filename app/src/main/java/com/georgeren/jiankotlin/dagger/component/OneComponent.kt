package com.georgeren.jiankotlin.dagger.component

import com.georgeren.jiankotlin.dagger.module.OneModule
import com.georgeren.jiankotlin.ui.fragment.OneFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by georgeRen on 2017/12/7.
 */
@Singleton
@Component(modules = arrayOf(OneModule::class))
interface OneComponent {
    fun inject(onFragment: OneFragment)
}