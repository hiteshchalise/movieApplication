package com.hites.movieapplication.data.datasource.remote

import androidx.lifecycle.LiveData
import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : DataSource {

    override fun getNowPlaying(): Observable<List<ResultMovie>> {
        return apiService.fetchNowPlaying().doOnNext {
            if (it == null) throw NullPointerException("Expression 'movieDao::insertMovies' must not be null")
            else movieDao.insertMovies(it)
        }
    }

}