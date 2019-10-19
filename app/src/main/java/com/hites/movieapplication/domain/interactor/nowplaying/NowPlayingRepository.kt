package com.hites.movieapplication.domain.interactor.nowplaying

import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable

interface NowPlayingRepository{
    fun getNowPlaying(): Observable<List<MoviePoster>>
}