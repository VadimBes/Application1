package com.art_int_labs.lead_up.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.art_int_labs.lead_up.data.db.entity.knowledge.KnowledgeList
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.KnowlegeDetailList
import com.art_int_labs.lead_up.data.db.entity.lesson.LessonList
import com.art_int_labs.lead_up.data.db.entity.main.ListNews
import com.art_int_labs.lead_up.internal.NoConnectivityException

class NetworkDataSourseImpl(
    private val apiLeadApiServices: ApiLeadApiServices
) : NetworkDataSourse {

    private val _downloadedNews = MutableLiveData<ListNews>()
    override val downloadedNews: LiveData<ListNews>
        get() = _downloadedNews

    override suspend fun getNews() {
        try {
            val getedImage = apiLeadApiServices.getNews().await()
            _downloadedNews.postValue(getedImage)
        }catch (e:NoConnectivityException){
            Log.d("Connectivity","No Internet",e)
        }
    }

    private val _downloadedKnowledges = MutableLiveData<KnowledgeList>()
    override val downloadedKnowledges: LiveData<KnowledgeList>
        get() = _downloadedKnowledges

    override suspend fun getKnowledges() {
        try{
            val getedKnowledges = apiLeadApiServices.getKnowledges().await()
            _downloadedKnowledges.postValue(getedKnowledges)
        }catch (e:NoConnectivityException){
            Log.d("Connectivity","No Internet",e)
        }
    }

    private val _downloadedLessons = MutableLiveData<LessonList>()
    override val downloadedLessons: LiveData<LessonList>
        get() = _downloadedLessons

    override suspend fun getLessons(id:Int) {
        try {
            val getLessons = apiLeadApiServices.getLesson(id).await()
            _downloadedLessons.postValue(getLessons)
        }catch (e:NoConnectivityException){
            Log.d("Connectivity","No Internet",e)
        }
    }

    private val _downloadedProduct = MutableLiveData<KnowlegeDetailList>()
    override val downloadedProduct: LiveData<KnowlegeDetailList>
        get() = _downloadedProduct

    override suspend fun getProduct(id: Int) {
        try {
            val getProduct = apiLeadApiServices.getProducts(id).await()
            _downloadedProduct.postValue(getProduct)
        }catch (e:NoConnectivityException){
            Log.d("Connectivity","No Internet",e)
        }
    }


}