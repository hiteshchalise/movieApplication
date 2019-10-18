package com.hites.movieapplication.data.datasource.local

import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable

class LocalDataSource(): DataSource {
    override fun getNowPlaying(): Observable<List<ResultMovie>> {
        TODO("not implemented")
    }
}