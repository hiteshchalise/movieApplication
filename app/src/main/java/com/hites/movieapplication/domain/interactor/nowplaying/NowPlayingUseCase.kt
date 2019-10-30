package com.hites.movieapplication.domain.interactor.nowplaying

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.model.MoviePoster

class NowPlayingUseCase(
    private val nowPlayingRepository: NowPlayingRepository
) : UseCase<List<MoviePoster>, NowPlayingUseCase.Params>() {

    override suspend fun runs(params: Params): Either<Failure, List<MoviePoster>> {
        return nowPlayingRepository.getNowPlaying(params.cached)
    }


    data class Params(val cached: Boolean)
}