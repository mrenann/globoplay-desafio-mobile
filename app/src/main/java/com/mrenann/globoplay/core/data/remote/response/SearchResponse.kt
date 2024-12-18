package com.mrenann.globoplay.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.globoplay.core.data.remote.model.SearchResult

data class SearchResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<SearchResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)