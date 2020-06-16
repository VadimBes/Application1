package com.art_int_labs.lead_up.data.db.entity.knowledge.detail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val heading: String,
    val id_knowledge: Int,
    val long_text: String,
    val picture: String
)