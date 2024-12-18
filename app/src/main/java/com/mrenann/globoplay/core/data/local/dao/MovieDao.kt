package com.mrenann.globoplay.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrenann.globoplay.core.data.local.entity.MediaEntity
import com.mrenann.globoplay.core.data.local.entity.MediaType
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM Medias")
    fun getMovies(): Flow<List<MediaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MediaEntity)

    @Query("SELECT * FROM Medias WHERE id = :movieId AND type = :type")
    suspend fun inList(movieId: Int, type: MediaType): MediaEntity?

    @Delete
    suspend fun deleteMovie(movie: MediaEntity)

}