package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.model.SimpleMovie
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either

interface DataSource{
    fun getNowPlaying(): Either<Failure, List<SimpleMovie>>
}
