package com.hites.movieapplication.data.datasource.local

import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.model.ApiResponse
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao): DataSource {
    override fun getNowPlaying(): Either<Failure, List<Movie>> {
//        return movieDao.getMovies()
        TODO()
    }
}