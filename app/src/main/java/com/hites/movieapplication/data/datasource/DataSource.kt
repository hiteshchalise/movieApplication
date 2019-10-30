package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import io.reactivex.Observable

interface DataSource{
    fun getNowPlaying(): Either<Failure, List<Movie>>
}
