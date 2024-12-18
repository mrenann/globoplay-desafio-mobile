package com.mrenann.globoplay.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mrenann.globoplay.KoinTestRule
import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.core.data.local.databases.ListDatabase
import com.mrenann.globoplay.core.data.local.entity.MediaEntity
import com.mrenann.globoplay.core.data.local.entity.MediaType
import com.mrenann.globoplay.di.TestAppModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Named

@SmallTest
class MediaDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(TestAppModule)
    )


    @Named("test_db")
    lateinit var database: ListDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            ListDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getMedias() = runTest {
        val medias: List<MediaEntity> = dao.getMovies().first()
        assertThat(medias.size).isEqualTo(0)
    }

    @Test
    fun insertAndCompareSize() = runTest {
        val mediaEntity = MediaEntity(
            id = 1,
            title = "",
            imageUrl = "",
            type = MediaType.MOVIE,
        )
        dao.insertMovie(mediaEntity)

        val medias: List<MediaEntity> = dao.getMovies().first()

        assertThat(medias.size).isEqualTo(1)
        assertThat(medias[0].id).isEqualTo(1)
    }

    @Test
    fun isInListWhenIsMarkedAsFavorite() = runTest {
        val mediaId = 1
        val mediaEntity = MediaEntity(
            id = mediaId,
            title = "FILMINHO",
            imageUrl = "",
            type = MediaType.MOVIE,
        )
        dao.insertMovie(mediaEntity)
        val isInList = dao.inList(
            mediaId,
            type = MediaType.MOVIE
        )

        assertThat(isInList).isEqualTo(mediaEntity)
    }

    @Test
    fun isNotInListWhenIsMarkedAsFavoriteReturnNull() = runTest {
        val mediaId = 5
        val mediaEntity = MediaEntity(
            id = 2,
            title = "FILMINHO",
            imageUrl = "",
            type = MediaType.MOVIE,
        )
        dao.insertMovie(mediaEntity)
        val isInList = dao.inList(
            mediaId,
            type = MediaType.MOVIE
        )

        assertThat(isInList).isNull()
    }

    @Test
    fun isNotInListWithTypeWhenIsMarkedAsFavoriteReturnNull() = runTest {
        val mediaId = 2
        val mediaEntity = MediaEntity(
            id = 2,
            title = "FILMINHO",
            imageUrl = "",
            type = MediaType.MOVIE,
        )
        dao.insertMovie(mediaEntity)
        val isInList = dao.inList(
            mediaId,
            type = MediaType.TV_SHOW
        )

        assertThat(isInList).isNull()
    }

    @Test
    fun addedReturnsObjectAndRemovesReturnsNull() = runTest {
        val mediaEntity = MediaEntity(
            id = 2,
            title = "FILMINHO",
            imageUrl = "",
            type = MediaType.MOVIE,
        )
        dao.insertMovie(mediaEntity)
        val allMedias: List<MediaEntity> = dao.getMovies().first()

        dao.deleteMovie(mediaEntity)

        val medias: List<MediaEntity> = dao.getMovies().first()


        assertThat(medias).isEmpty()
        assertThat(allMedias).isNotEmpty()
    }

}