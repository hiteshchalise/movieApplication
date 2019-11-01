package com.hites.movieapplication.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hites.movieapplication.data.model.MovieDTO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieDTO>)

    @Query("SELECT * FROM MovieDTO")
    fun getMovies(): List<MovieDTO>

}