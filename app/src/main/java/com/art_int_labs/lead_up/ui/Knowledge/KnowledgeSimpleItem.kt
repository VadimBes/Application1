package com.art_int_labs.lead_up.ui.Knowledge

import android.annotation.SuppressLint
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.knowledge.Knowledge
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.knowledge_item.*

class KnowledgeSimpleItem(
    val knowledge: Knowledge
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            updateImage()
            title.text = knowledge.heading
            tovar.text = "${knowledge.count_product} товара"
        }
    }

    override fun getLayout() = R.layout.knowledge_item

    @SuppressLint("CheckResult")
    private fun GroupieViewHolder.updateImage(){
        Glide.with(this.itemView).load(knowledge.picture).into(mainImage)
    }
}