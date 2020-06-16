package com.art_int_labs.lead_up.ui.Knowledge.detail

import android.annotation.SuppressLint
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.Product
import com.art_int_labs.lead_up.internal.ImageRequester
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.knowledge_detail_item.*
import kotlinx.android.synthetic.main.lesson_item.*

class KnowledgeDetailSimpleItem(
    val product: Product
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            updateImage()
            name_product.text = product.heading
        }
    }

    override fun getLayout() = R.layout.knowledge_detail_item

    @SuppressLint("CheckResult")
    private fun GroupieViewHolder.updateImage(){
       // Glide.with(this.itemView).load(product.picture).centerCrop().into(icon_product)
        ImageRequester.setImageFromUrl(icon_product, product.picture)
    }
}