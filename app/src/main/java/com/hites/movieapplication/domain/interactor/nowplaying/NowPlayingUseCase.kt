package com.hites.movieapplication.domain.interactor.nowplaying

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository
) : UseCase<List<Movie>, NowPlayingUseCase.Params>() {

    override suspend fun runs(params: Params): Either<Failure, List<Movie>> {
        return nowPlayingRepository.getNowPlaying(params.cached)
    }


    data class Params(val cached: Boolean)
}