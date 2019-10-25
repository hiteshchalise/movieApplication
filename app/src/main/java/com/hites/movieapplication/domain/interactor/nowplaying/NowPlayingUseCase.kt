package com.hites.movieapplication.domain.interactor.nowplaying

import android.util.Log
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.executor.PostExecutionThread
import com.hites.movieapplication.domain.executor.ThreadExecutor
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable
import javax.inject.Inject

class NowPlayingUseCase(
    private val nowPlayingRepository: NowPlayingRepository
) : UseCase<List<MoviePoster>, Void>() {

    override suspend fun runs(params: Void?): Either<Failure, List<MoviePoster>> {
        return nowPlayingRepository.getNowPlaying()
    }
}