package com.art_int_labs.lead_up.data.db.entity.main

import androidx.room.Entity

@Entity
data class ListNews(
    val courses: List<Course>,
    val news: List<News>
)