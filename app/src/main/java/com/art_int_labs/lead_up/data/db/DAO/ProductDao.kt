package com.art_int_labs.lead_up.data.db.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.Product
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(product: List<Product>)

    @Query("select * from Product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("select * from product where id_knowledge = :id")
    fun getProductById(id:Int): LiveData<List<Product>>

//    @Query("select exists(select id from Lesson where id_courses = :id)")
//    fun getLessonByIdList(id:Int):Boolean

    @Query("delete from product")
    fun deleteAllProduct()
}