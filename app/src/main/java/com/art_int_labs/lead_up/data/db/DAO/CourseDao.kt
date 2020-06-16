package com.art_int_labs.lead_up.data.db.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.art_int_labs.lead_up.data.db.entity.main.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(courses: List<Course>)

    @Query("select * from Course ")
    fun getCourses(): LiveData<List<Course>>

    @Query("select * from Course where id = :id ")
    fun getCourseById(id: Int): Course

    @Query("delete from Course")
    fun deleteAllCourses()

}