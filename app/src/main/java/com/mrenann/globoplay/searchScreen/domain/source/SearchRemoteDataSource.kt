package com.mrenann.globoplay.searchScreen.domain.source

import com.mrenann.globoplay.core.data.remote.response.SearchResponse
import com.mrenann.globoplay.core.paging.SearchPagingSource

interface SearchRemoteDataSource {
    fun getPagingSource(query: String): SearchPagingSource

    suspend fun getSearch(
        page: Int,
        query: String,
    ): SearchResponse
}
