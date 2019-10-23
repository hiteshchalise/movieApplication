package com.hites.movieapplication.domain.interactor.nowplaying

import com.hites.movieapplication.domain.executor.PostExecutionThread
import com.hites.movieapplication.domain.executor.ThreadExecutor
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.Observable
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<MoviePoster>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<List<MoviePoster>> {
        return nowPlayingRepository.getNowPlaying()
    }
}