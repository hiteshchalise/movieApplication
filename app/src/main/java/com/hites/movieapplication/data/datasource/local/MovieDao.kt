package com.hites.movieapplication.data.datasource.local

import androidx.room.*
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovies(nowPlayingMovies: List<NowPlayingMovieDTO>)

    @Query("SELECT * FROM NowPlayingMovieDTO")
    fun getNowPlayingMovies(): List<NowPlayingMovieDTO>

    @Query("DELETE FROM NowPlayingMovieDTO")
    fun removeNowPlayingMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(nowPlayingMovies: List<PopularMovieDTO>)

    @Query("SELECT * FROM NowPlayingMovieDTO")
    fun getPopularMovies(): List<PopularMovieDTO>

}