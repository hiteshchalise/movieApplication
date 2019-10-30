package com.hites.movieapplication.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hites.movieapplication.data.model.SimpleMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<SimpleMovie>)

    @Query("SELECT * FROM SimpleMovie")
    fun getMovies(): List<SimpleMovie>

}