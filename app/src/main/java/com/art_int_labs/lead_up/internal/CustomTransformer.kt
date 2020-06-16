package com.art_int_labs.lead_up.internal

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CustomTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {

        val r = 1 - Math.abs(position)
        page.translationY = -Math.abs(position) * 110f
        page.scaleY = 0.85f + r *0.25f
        page.scaleX = 0.85f + r *0.25f
    }
}