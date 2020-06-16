package com.art_int_labs.lead_up.data.db.entity.lesson

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Lesson")
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id:Int,
    val heading: String,
    val id_courses: Int,
    val long_text: String,
    val picture: String,
    val video:String?
)