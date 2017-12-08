package com.georgeren.jiankotlin.ui.adapter

import android.content.Intent
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.georgeren.jiankotlin.R
import com.georgeren.jiankotlin.ui.activity.StoryDetailActivity
import com.georgeren.jiankotlin.ui.model.Story

/**
 * Created by georgeRen on 2017/12/5.
 */
class StoryAdapter(layoutResId: Int, data: List<Story>?) : BaseQuickAdapter<Story, BaseViewHolder>(layoutResId, data) {
    override fun convert(vh: BaseViewHolder, story: Story?) {
        if (story != null) {
            vh.setText(R.id.tv_story_title, story.title)
                    .setText(R.id.tv_story_time, story.date)
                    .setOnClickListener(R.id.rl_item_recommend) {
                        mContext.startActivity(Intent(mContext, StoryDetailActivity::class.java)
                                .putExtra("storyId", story.id)
                                .putExtra("storyTitle", story.title))
                    }
            Glide.with(mContext).load(story.images[0]).into(vh.getView(R.id.iv_story_image))
        }
    }
}