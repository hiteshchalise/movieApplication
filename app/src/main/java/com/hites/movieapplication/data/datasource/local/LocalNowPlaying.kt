package com.hites.movieapplication.data.datasource.local

import android.util.Log
import com.hites.movieapplication.data.datasource.NowPlayingDataSource
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject

class LocalNowPlaying @Inject constructor(private val movieDao: MovieDao) : NowPlayingDataSource {
    override fun getNowPlaying(): Either<Failure, List<NowPlayingMovieDTO>> {
        val nowPlayingMovies = movieDao.getNowPlayingMovies()
        Log.d("MovieApplication", "LocalDataSource: $nowPlayingMovies")
        return Either.Right(nowPlayingMovies)
    }
}