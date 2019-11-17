package com.hites.movieapplication.data.datasource.remote

import android.util.Log
import com.hites.movieapplication.data.datasource.GetPopularMoviesDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.request
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject
import javax.inject.Named

class RemoteGetPopularMovies @Inject constructor(
    @Named("EnvelopingApi")
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : GetPopularMoviesDataSource {

    override fun getPopularMovies(cached: Boolean): Either<Failure, List<PopularMovieDTO>> {
        if (cached) {
            Log.d("MovieApplication", "getPopularMovies: from cache")
            val popularMovies = movieDao.getPopularMovies()
            return when (popularMovies == null) {
                true -> Either.Left(Failure.NetworkConnection)
                false -> Either.Right(popularMovies)
            }
        }

        val responseEither = request(apiService.fetchPopularMovies(), emptyList())
        responseEither.either({}, {
            movieDao.removePopularMovies()
            movieDao.insertPopularMovies(it)
        })
        return responseEither
    }
}