package com.mrenann.globoplay.core.data.local.entity

import androidx.room.Entity

enum class MediaType {
    MOVIE,
    TV_SHOW
}

@Entity(
    tableName = "Medias",
    primaryKeys = ["id", "type"] // Composite key with 'id' and 'type'
)
data class MediaEntity(
    val id: Int, // Either movieId or tvId
    val title: String,
    val imageUrl: String,
    val type: MediaType
)