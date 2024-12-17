package com.mrenann.globoplay.homeScreen.domain.source

import androidx.paging.PagingSource
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.Media

interface DiscoverRemoteDataSource<T> {
    fun getPagingSource(): PagingSource<Int, Media>
    fun getBrazilianPagingSource(): PagingSource<Int, Media>

    suspend fun getDiscover(
        page: Int,
        isFromBrazil: Boolean = false
    ): DiscoverMediaResponse<T>
}
