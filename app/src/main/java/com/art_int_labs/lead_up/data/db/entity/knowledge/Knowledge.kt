package com.art_int_labs.lead_up.data.db.entity.knowledge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Knowledge")
data class Knowledge(
    @SerializedName("count_product")
    val count_product: Int,
    val heading: String,
    @PrimaryKey
    val id: Int,
    val picture: String
)