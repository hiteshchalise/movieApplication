package com.hites.movieapplication.data.datasource.local

import android.util.Log
import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.model.MovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao): DataSource {
    override fun getNowPlaying(): Either<Failure, List<MovieDTO>> {
        val movies = movieDao.getMovies()
        Log.d("MovieApplication", "LocalDataSource $movies")
        return Either.Right(movies)
    }
}