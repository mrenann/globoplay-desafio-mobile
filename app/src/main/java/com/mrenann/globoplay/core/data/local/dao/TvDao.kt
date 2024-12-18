package com.mrenann.globoplay.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrenann.globoplay.core.data.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Query("SELECT * FROM Movies")
    fun getSeries(): Flow<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(Tv: TvEntity)

    @Query("SELECT * FROM Series WHERE tvId = :tvId")
    suspend fun inList(tvId: Int): TvEntity?

    @Delete
    suspend fun deleteSerie(movie: TvEntity)

}