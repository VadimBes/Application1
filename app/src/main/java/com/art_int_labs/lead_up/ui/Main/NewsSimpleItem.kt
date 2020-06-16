package com.art_int_labs.lead_up.ui.Main

import android.annotation.SuppressLint
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.main.News
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.news_item.*


class NewsSimpleItem(
    val news: News
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            updateImage()
            shortText.text = news.short_text
        }
    }

    override fun getLayout() = R.layout.news_item

    @SuppressLint("CheckResult")
    private fun GroupieViewHolder.updateImage(){
        Glide.with(this.itemView).load(news.picture).into(circleImageView)
    }
}