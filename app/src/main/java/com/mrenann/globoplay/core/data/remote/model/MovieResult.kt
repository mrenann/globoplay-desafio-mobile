package com.mrenann.globoplay.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
) : MediaResult()
