package com.hites.movieapplication.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<ResultMovie>)

    @Query("SELECT * FROM ResultMovie")
    fun getMovies(): LiveData<List<ResultMovie>>

}