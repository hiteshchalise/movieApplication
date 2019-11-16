package com.hites.movieapplication.data.datasource.remote

import com.hites.movieapplication.data.datasource.GetPopularMoviesDataSource
import com.hites.movieapplication.data.datasource.NowPlayingDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.request
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import javax.inject.Inject
import javax.inject.Named



class RemoteNowPlaying @Inject constructor(
    @Named("EnvelopingApi")
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : NowPlayingDataSource {

    override fun getNowPlaying(): Either<Failure, List<NowPlayingMovieDTO>> {
        val responseEither = request(apiService.fetchNowPlaying(), emptyList())
        responseEither.either({}, {
            movieDao.removeNowPlayingMovies()
            movieDao.insertNowPlayingMovies(it)
        })
        return responseEither
    }
}