package com.mrenann.globoplay.searchScreen.data.source

import com.mrenann.globoplay.core.data.remote.MediaService
import com.mrenann.globoplay.core.data.remote.response.SearchResponse
import com.mrenann.globoplay.core.paging.SearchPagingSource
import com.mrenann.globoplay.searchScreen.domain.source.SearchRemoteDataSource

class SearchRemoteDataSourceImpl(
    private val service: MediaService,
) : SearchRemoteDataSource {

    override fun getPagingSource(query: String): SearchPagingSource {
        return SearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearch(page: Int, query: String): SearchResponse {
        return service.search(page, query)
    }
}
