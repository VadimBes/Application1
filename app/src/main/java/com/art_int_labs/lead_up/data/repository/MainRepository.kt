package com.art_int_labs.lead_up.data.repository

import androidx.lifecycle.LiveData
import com.art_int_labs.lead_up.data.db.entity.knowledge.Knowledge
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.Product
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.art_int_labs.lead_up.data.db.entity.main.ListNews
import com.art_int_labs.lead_up.data.db.entity.main.News

interface MainRepository {
    suspend fun getNews():LiveData<ListNews>
    suspend fun getCurrentNews(): LiveData<List<News>>
    suspend fun getCurrentCourses(): LiveData<List<Course>>
    suspend fun getCurrentKnowledges():LiveData<List<Knowledge>>
    suspend fun getLessons(id:Int):LiveData<List<Lesson>>
    suspend fun getProducts(id:Int):LiveData<List<Product>>
}