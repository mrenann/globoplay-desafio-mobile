package com.mrenann.globoplay.core.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrenann.globoplay.core.data.local.dao.MovieDao
import com.mrenann.globoplay.core.data.local.dao.TvDao
import com.mrenann.globoplay.core.data.local.entity.MediaEntity

@Database(
    entities = [MediaEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ListDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}