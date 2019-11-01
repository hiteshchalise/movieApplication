package com.hites.movieapplication.data.datasource.local

import android.util.Log
import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao): DataSource {
    override fun getPopularMovies(): Either<Failure, List<PopularMovieDTO>> {
        val popularMovies = movieDao.getPopularMovies()
        Log.d("MovieApplication", "LocalDataSource: $popularMovies")
        return Either.Right(popularMovies)
    }

    override fun getNowPlaying(): Either<Failure, List<NowPlayingMovieDTO>> {
        val nowPlayingMovies = movieDao.getNowPlayingMovies()
        Log.d("MovieApplication", "LocalDataSource: $nowPlayingMovies")
        return Either.Right(nowPlayingMovies)
    }
}