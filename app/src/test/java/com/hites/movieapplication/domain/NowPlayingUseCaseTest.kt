package com.hites.movieapplication.domain

import com.hites.movieapplication.domain.executor.PostExecutionThread
import com.hites.movieapplication.domain.executor.ThreadExecutor
import com.hites.movieapplication.domain.model.MoviePoster
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Test

class NowPlayingUseCaseTest{
    private val nowPlayingRepository: NowPlayingRepository = mockk()
    private val threadExecutor: ThreadExecutor = mockk()
    private val postExecutionThread: PostExecutionThread = mockk()
    private val nowPlayingUseCase =
        NowPlayingUseCase(
            nowPlayingRepository,
            threadExecutor,
            postExecutionThread
        )
    private val observableMoviePosterList: Observable<List<MoviePoster>> = mockk()

    @Test
    fun `repository returns properly`(){
        // Given
        every { nowPlayingRepository.getNowPlaying() } returns observableMoviePosterList

        // When
        val nowPlaying = nowPlayingUseCase.buildUseCaseObservable(null)

        // Then
        assertEquals(nowPlaying, observableMoviePosterList)
        verify{nowPlayingUseCase.buildUseCaseObservable(null)}
    }

}