package com.mrenann.globoplay.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.globoplay.core.data.remote.model.Video

data class VideosResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("results")
    val results: List<Video> = listOf(),
)