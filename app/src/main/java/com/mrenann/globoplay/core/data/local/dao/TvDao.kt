package com.mrenann.globoplay.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrenann.globoplay.core.data.local.entity.MediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Query("SELECT * FROM Medias")
    fun getSeries(): Flow<List<MediaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(Tv: MediaEntity)

    @Query("SELECT * FROM Medias WHERE id = :tvId AND type = 'TV_SHOW'")
    suspend fun inList(tvId: Int): MediaEntity?

    @Delete
    suspend fun deleteSerie(movie: MediaEntity)

}