package com.hites.movieapplication.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovies(nowPlayingMovies: List<NowPlayingMovieDTO>)

    @Query("SELECT * FROM NowPlayingMovieDTO")
    fun getNowPlayingMovies(): List<NowPlayingMovieDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(nowPlayingMovies: List<PopularMovieDTO>)

    @Query("SELECT * FROM NowPlayingMovieDTO")
    fun getPopularMovies(): List<PopularMovieDTO>

}