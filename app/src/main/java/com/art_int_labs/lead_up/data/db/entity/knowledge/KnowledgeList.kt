package com.art_int_labs.lead_up.data.db.entity.knowledge

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class KnowledgeList(
    @SerializedName("result")
    val knowledge: List<Knowledge>
)