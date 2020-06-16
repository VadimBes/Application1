package com.art_int_labs.lead_up.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.art_int_labs.lead_up.data.db.DAO.*
import com.art_int_labs.lead_up.data.db.entity.knowledge.Knowledge
import com.art_int_labs.lead_up.data.db.entity.knowledge.KnowledgeList
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.KnowlegeDetailList
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.Product
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson
import com.art_int_labs.lead_up.data.db.entity.lesson.LessonList
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.art_int_labs.lead_up.data.network.NetworkDataSourse
import com.art_int_labs.lead_up.data.db.entity.main.ListNews
import com.art_int_labs.lead_up.data.db.entity.main.News
import kotlinx.coroutines.*
import java.lang.Exception

class MainRepositoryImpl(
    private val networkDataSourse: NetworkDataSourse,
    private val courseDao: CourseDao,
    private val newsDao: NewsDao,
    private val knowledgeDao: KnowledgeDao,
    private val lessonDao: LessonDao,
    private val productDao: ProductDao
) : MainRepository {

    init {
        networkDataSourse.downloadedNews.observeForever( Observer {
            persistNewsAndCourses(it)
        })
        networkDataSourse.downloadedKnowledges.observeForever(Observer {
            persistKnowledges(it)
        })
        networkDataSourse.downloadedLessons.observeForever{
            persistLessons(it)
        }
        networkDataSourse.downloadedProduct.observeForever {
            persistProduct(it)
        }
    }

    override suspend fun getNews(): LiveData<ListNews> {
        initNewsCourse()
        return networkDataSourse.downloadedNews
    }

    private suspend fun fetchNewsAndCourses(){
        networkDataSourse.getNews()
    }

    private suspend fun fetchLessons(id: Int){
        networkDataSourse.getLessons(id)
    }

    private suspend fun fetchProducts(id: Int){
        networkDataSourse.getProduct(id)
    }

    override suspend fun getCurrentNews(): LiveData<List<News>> {
        initNewsCourse()
        return newsDao.getNews()
    }

    override suspend fun getCurrentCourses(): LiveData<List<Course>> {
        initNewsCourse()
        return courseDao.getCourses()
    }

    override suspend fun getCurrentKnowledges(): LiveData<List<Knowledge>> {
        initKnowledge()
        return knowledgeDao.getKnowledges()
    }

    override suspend fun getLessons(id: Int): LiveData<List<Lesson>> {
        initLessons(id)
        return lessonDao.getLessonById(id)
    }

    override suspend fun getProducts(id: Int): LiveData<List<Product>> {
        initProduct(id)
        return productDao.getProductById(id)
    }

    private suspend fun fetchKnowledges(){
        networkDataSourse.getKnowledges()
    }

    private fun persistKnowledges(knowledge: KnowledgeList){
        GlobalScope.launch(Dispatchers.IO) {
            knowledgeDao.upsert(knowledge.knowledge)
        }
    }

    private fun persistLessons(lessons:LessonList){
        GlobalScope.launch(Dispatchers.IO) {
            lessonDao.upsert(lessons.result)
        }
    }

    private fun persistProduct(products: KnowlegeDetailList){
        GlobalScope.launch(Dispatchers.IO) {
            productDao.upsert(products.result)
        }
    }

    private fun persistNewsAndCourses(newsAndCourses: ListNews) {
        GlobalScope.launch(Dispatchers.IO) {
            newsDao.upsert(newsAndCourses.news)
            courseDao.upsert(newsAndCourses.courses)
        }
    }

    private suspend fun initLessons(id:Int){
        if (!isFetchNeededLesson(id)) fetchLessons(id)
    }

    private suspend fun initProduct(id: Int){
        if (isFetchNeeded()) fetchProducts(id)
    }

    private suspend fun initNewsCourse() {
        if (isFetchNeeded())
            fetchNewsAndCourses()
    }

    private suspend fun initKnowledge(){
        if (isFetchNeededKnowledge()) fetchKnowledges()
    }


    private fun isFetchNeeded(): Boolean {
//        val rand = Random.nextInt(0, 2)
//        return if (rand == 0) true
//        else false
        return true
    }

    private fun isFetchNeededLesson(id:Int):Boolean{
        var need:Boolean = false
        try {
            runBlocking {
            launch(Dispatchers.Main){
                withContext(Dispatchers.IO){
                   need = lessonDao.getLessonByIdList(id)
                }
            } }
        }catch (e:Exception){
            Log.d("MYTAG",e.localizedMessage)
        }
        return need
    }
    private fun isFetchNeededKnowledge():Boolean{
        return true
    }
}