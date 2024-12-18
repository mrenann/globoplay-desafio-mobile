package com.mrenann.globoplay.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mrenann.globoplay.core.domain.model.Media
import com.mrenann.globoplay.homeScreen.data.mapper.toSerieFromTv
import com.mrenann.globoplay.mediaDetailsScreen.domain.source.TvDetailsRemoteDataSource
import java.io.IOException

class TvSimilarPagingSource(
    private val remoteDataSource: TvDetailsRemoteDataSource,
    private val id: Int,
) : PagingSource<Int, Media>() {
    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(LIMIT_PAGE) ?: page?.nextKey?.minus(
                LIMIT_PAGE
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val page = params.key ?: 1
            val response =
                remoteDataSource.getTvSeriesSimilar(page = page, id = id)
            val series = response.results

            LoadResult.Page(
                data = series.toSerieFromTv(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (series.isEmpty()) null else page + 1,
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
        private const val LIMIT_PAGE = 20
    }

}