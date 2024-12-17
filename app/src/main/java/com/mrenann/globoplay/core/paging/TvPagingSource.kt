package com.mrenann.globoplay.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mrenann.globoplay.core.data.remote.model.TVResult
import com.mrenann.globoplay.core.data.remote.response.DiscoverMediaResponse
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.homeScreen.data.mapper.toSerieFromTv
import com.mrenann.globoplay.homeScreen.domain.source.TvDiscoverRemoteDataSource
import java.io.IOException

class TvPagingSource(
    private val remoteDataSource: TvDiscoverRemoteDataSource
) : PagingSource<Int, Media>() {


    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(LIMIT_PAGE) ?: page?.nextKey?.minus(LIMIT_PAGE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val page = params.key ?: 1
            val response: DiscoverMediaResponse<TVResult> =
                remoteDataSource.getDiscover(page = page)
            val tvSeries = response.results


            LoadResult.Page(
                data = tvSeries.toSerieFromTv(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (tvSeries.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT_PAGE = 10
    }


}