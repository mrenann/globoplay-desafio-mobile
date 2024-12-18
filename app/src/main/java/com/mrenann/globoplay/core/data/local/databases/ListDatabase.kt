package com.mrenann.globoplay.core.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.core.data.local.dao.TvDao
import com.mrenann.globoplay.core.data.local.entity.MovieEntity
import com.mrenann.globoplay.core.data.local.entity.TvEntity

@Database(
    entities = [MovieEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ListDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}