package com.hites.movieapplication.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hites.movieapplication.data.model.SimpleMovie

@Database(entities = [SimpleMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao
}