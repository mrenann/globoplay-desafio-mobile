package com.mrenann.globoplay.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.globoplay.core.data.remote.model.TVResult

data class DiscoverTVResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<TVResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)