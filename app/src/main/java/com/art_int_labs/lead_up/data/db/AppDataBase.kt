package com.art_int_labs.lead_up.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.art_int_labs.lead_up.data.db.DAO.*
import com.art_int_labs.lead_up.data.db.entity.knowledge.Knowledge
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.Product
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.art_int_labs.lead_up.data.db.entity.main.News

@Database(entities = [Course::class, News::class,Knowledge::class,Lesson::class,Product::class],version = 1)

abstract class AppDataBase: RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun newsDao(): NewsDao
    abstract fun knowledgeDao():KnowledgeDao
    abstract fun lessonDao():LessonDao
    abstract fun productDao():ProductDao

    companion object{
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDataBase(context).also { instance = it }
        }

        fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java,"app_data_base.db").build()

    }

}