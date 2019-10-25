package com.hites.movieapplication.data.datasource.local

import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao): DataSource {
    override fun getNowPlaying(): Observable<List<ResultMovie>> {
        val movies = movieDao.getMovies()
        return if (movies.isEmpty()) {
            Observable.empty()
        } else {
            Observable.just(movies)
        }
    }
}