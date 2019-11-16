package com.hites.movieapplication.data.datasource.remote

import com.hites.movieapplication.data.datasource.GetDetailsMoviesDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.request
import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject
import javax.inject.Named

class RemoteGetDetails @Inject constructor(
    @Named("NonEnvelopingApi")
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : GetDetailsMoviesDataSource {

    override fun getDetails(id: Int): Either<Failure, MovieDetailsDTO> {
        val responseEither = request(apiService.fetchMovieDetails(id), MovieDetailsDTO.EMPTY)
        responseEither.either({}, {
            movieDao.insertMovieDetails(it)
        })
        return responseEither
    }

}