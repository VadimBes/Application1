package com.art_int_labs.lead_up.ui.Course.detail

import android.annotation.SuppressLint
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.couse_item.*
import kotlinx.android.synthetic.main.lesson_item.*

class CourseDetailSimpleItem(
    val lesson: Lesson
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            name_lesson.text = lesson.heading
        }
    }

    override fun getLayout() = R.layout.lesson_item


}