package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either

interface DataSource{
    fun getNowPlaying(): Either<Failure, List<NowPlayingMovieDTO>>
    fun getPopularMovies(): Either<Failure, List<PopularMovieDTO>>
}
