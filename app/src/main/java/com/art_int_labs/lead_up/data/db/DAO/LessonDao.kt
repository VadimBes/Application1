package com.art_int_labs.lead_up.data.db.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson
import com.art_int_labs.lead_up.data.db.entity.lesson.LessonList

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(lessons:List<Lesson>)

    @Query("select * from Lesson")
    fun getAllLessons():LiveData<List<Lesson>>

    @Query("select * from Lesson where id_courses = :id")
    fun getLessonById(id:Int):LiveData<List<Lesson>>

    @Query("select exists(select id from Lesson where id_courses = :id)")
    fun getLessonByIdList(id:Int):Boolean

    @Query("delete from lesson")
    fun deleteAllLesson()
}