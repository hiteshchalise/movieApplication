package com.hites.movieapplication.data.datasource.remote

import android.util.Log
import com.hites.movieapplication.data.datasource.NowPlayingDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.request
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject
import javax.inject.Named


class RemoteNowPlaying @Inject constructor(
    @Named("EnvelopingApi")
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : NowPlayingDataSource {

    override fun getNowPlaying(cached: Boolean): Either<Failure, List<NowPlayingMovieDTO>> {
        if (cached) {
            Log.d("MovieApplication", "getNowPlaying: from cache")
            val nowPlayingMovies = movieDao.getNowPlayingMovies()
            return when (nowPlayingMovies == null) {
                true -> Either.Left(Failure.NetworkConnection)
                false -> Either.Right(nowPlayingMovies)
            }
        }
        val responseEither = request(apiService.fetchNowPlaying(), emptyList())
        responseEither.either({}, {
            movieDao.removeNowPlayingMovies()
            movieDao.insertNowPlayingMovies(it)
        })
        return responseEither
    }
}