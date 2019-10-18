package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import io.reactivex.Observable

interface DataSource{
    fun getNowPlaying(): Observable<List<ResultMovie>>
}
