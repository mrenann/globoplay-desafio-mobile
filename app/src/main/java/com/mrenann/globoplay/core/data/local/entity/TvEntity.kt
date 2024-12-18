package com.mrenann.globoplay.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Series"
)
class TvEntity(
    @PrimaryKey(autoGenerate = false)
    val tvId: Int,
    val title: String,
    val imageUrl: String,
)