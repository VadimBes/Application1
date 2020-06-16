package com.art_int_labs.lead_up.ui.Course

import android.annotation.SuppressLint
import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.couse_item.*

class CourseFragSimpleItem(
    val course: Course
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            updateImage()
            title_card.text = course.heading
            count_course.text = "${course.count_lesson}"
            count_ball.text = "${course.balls}"
        }
    }

    override fun getLayout() = R.layout.couse_item

    @SuppressLint("CheckResult")
    private fun GroupieViewHolder.updateImage(){
        Glide.with(this.itemView).load(course.picture).into(circleImageView)
    }
}