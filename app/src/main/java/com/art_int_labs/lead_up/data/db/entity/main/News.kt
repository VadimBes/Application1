package com.art_int_labs.lead_up.data.db.entity.main

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "News")
data class News(
    @PrimaryKey
    val id: Int,
    val picture: String,
    @SerializedName("short_text")
    val short_text: String
)