package com.art_int_labs.lead_up.data.db.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.art_int_labs.lead_up.data.db.entity.knowledge.Knowledge

@Dao
interface KnowledgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(knowledges:List<Knowledge>)

    @Query("select * from Knowledge")
    fun getKnowledges():LiveData<List<Knowledge>>

    @Query("select * from Knowledge where id = :id")
    fun getKnowledgeById(id:Int):Knowledge

    @Query("Delete from Knowledge")
    fun deleteAllKnowledges()
}