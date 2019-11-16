package com.hites.movieapplication.data.datasource.local

import android.util.Log
import com.hites.movieapplication.data.datasource.GetPopularMoviesDataSource
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject


class LocalGetPopularMovies @Inject constructor(private val movieDao: MovieDao) : GetPopularMoviesDataSource {

    override fun getPopularMovies(): Either<Failure, List<PopularMovieDTO>> {
        val popularMovies = movieDao.getPopularMovies()
        Log.d("MovieApplication", "LocalDataSource: $popularMovies")
        return Either.Right(popularMovies)
    }
}