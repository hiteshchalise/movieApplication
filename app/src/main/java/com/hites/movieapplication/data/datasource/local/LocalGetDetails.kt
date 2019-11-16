package com.hites.movieapplication.data.datasource.local

import com.hites.movieapplication.data.datasource.GetDetailsMoviesDataSource
import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject


class LocalGetDetails @Inject constructor(private val movieDao: MovieDao) : GetDetailsMoviesDataSource {

    override fun getDetails(id: Int): Either<Failure, MovieDetailsDTO> {
        val movieDetails: MovieDetailsDTO = movieDao.getMovieDetails(id)
        return Either.Right(movieDetails)
    }
}