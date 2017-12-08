package com.georgeren.jiankotlin.dagger.component

import com.georgeren.jiankotlin.dagger.module.StoryModule
import com.georgeren.jiankotlin.ui.activity.StoryDetailActivity
import com.georgeren.jiankotlin.ui.fragment.StoryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by georgeRen on 2017/12/5.
 */
@Singleton
@Component(modules = arrayOf(StoryModule::class))
interface StoryComponent {
    fun inject(storyDetailActivity: StoryDetailActivity)

    fun inject(storyFragment: StoryFragment)
}