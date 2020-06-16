package com.art_int_labs.lead_up.data.network

import androidx.lifecycle.LiveData
import com.art_int_labs.lead_up.data.db.entity.knowledge.KnowledgeList
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.KnowlegeDetailList
import com.art_int_labs.lead_up.data.db.entity.lesson.LessonList
import com.art_int_labs.lead_up.data.db.entity.main.ListNews

interface NetworkDataSourse {
    val downloadedNews:LiveData<ListNews>
    suspend fun getNews()
    val downloadedKnowledges:LiveData<KnowledgeList>
    suspend fun getKnowledges()
    val downloadedLessons:LiveData<LessonList>
    suspend fun  getLessons(id:Int)
    val downloadedProduct:LiveData<KnowlegeDetailList>
    suspend fun  getProduct(id:Int)

}