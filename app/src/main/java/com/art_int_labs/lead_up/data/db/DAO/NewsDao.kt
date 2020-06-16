package com.art_int_labs.lead_up.data.db.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.art_int_labs.lead_up.data.db.entity.main.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(courses: List<News>)

    @Query("select * from News ")
    fun getNews(): LiveData<List<News>>

    @Query("select * from News where id = :id ")
    fun getNewsById(id: Int): News

    @Query("delete from News")
    fun deleteAllNews()
}