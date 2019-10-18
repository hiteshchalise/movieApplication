package com.hites.movieapplication.domain.nowplaying

import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable

class NowPlayingUseCase(private val nowPlayingRepository: NowPlayingRepository) {
    fun getNowPlaying(): Observable<List<MoviePoster>>{
        return nowPlayingRepository.getNowPlaying()
    }
}