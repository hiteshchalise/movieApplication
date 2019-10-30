package com.hites.movieapplication.domain.interactor.nowplaying

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.MoviePoster

interface NowPlayingRepository{
    fun getNowPlaying(): Either<Failure, List<MoviePoster>>
}