package com.art_int_labs.lead_up.ui.Main

import android.annotation.SuppressLint
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.predl_course_item.*

class CourseSimpleItem(
    val course: Course
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            updateImage()
            name.text = course.heading
            count_lesson.text = "${course.count_lesson} урока"
        }
    }

    override fun getLayout() = R.layout.predl_course_item

    @SuppressLint("CheckResult")
    private fun GroupieViewHolder.updateImage(){
        Glide.with(this.itemView).load(course.picture).into(icon_course)
    }
}