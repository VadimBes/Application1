package com.art_int_labs.lead_up.data.network

import com.art_int_labs.lead_up.data.db.entity.autorization.Authentication
import com.art_int_labs.lead_up.data.db.entity.autorization.Login
import com.art_int_labs.lead_up.data.db.entity.autorization.LoginList
import com.art_int_labs.lead_up.data.db.entity.knowledge.KnowledgeList
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.KnowlegeDetailList
import com.art_int_labs.lead_up.data.db.entity.lesson.LessonList
import com.art_int_labs.lead_up.data.db.entity.main.ListNews
import com.art_int_labs.lead_up.data.db.entity.main.News
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiLeadApiServices {

    @GET("main")
    fun getNews(): Deferred<ListNews>

    @GET("main")
    fun getCourse():Deferred<News>

    @POST("knowledge")
    fun getKnowledges():Deferred<KnowledgeList>

    @GET("courses/click?")
    fun getLesson(
    @Query("id") id:Int
    ):Deferred<LessonList>

    @GET("knowledge/click?")
    fun  getProducts(
    @Query("id") id:Int
    ):Deferred<KnowlegeDetailList>

    @POST("enterWorker")
    fun authentication(@Body authentication: Authentication): Deferred <LoginList>


    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiLeadApiServices {
            val requestInterceptor = Interceptor{chain->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiLeadApiServices::class.java)
        }
    }
}