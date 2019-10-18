package com.hites.movieapplication.domain

import com.hites.movieapplication.domain.model.MoviePoster
import com.hites.movieapplication.domain.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.nowplaying.NowPlayingUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Test

class NowPlayingUseCaseTest{
    private val nowPlayingRepository: NowPlayingRepository = mockk()
    private val nowPlayingUseCase =
        NowPlayingUseCase(nowPlayingRepository)
    private val listMovieLiveData: Observable<List<MoviePoster>> = mockk()

    @Test
    fun `repository returns properly`(){
        // Given
        every { nowPlayingRepository.getNowPlaying() } returns listMovieLiveData

        // When
        val nowPlaying = nowPlayingUseCase.getNowPlaying()

        // Then
        assertEquals(nowPlaying, listMovieLiveData)
        verify{nowPlayingUseCase.getNowPlaying()}
    }

//    @Test
//    fun `fetchMore method calls fetchMore in repository`(){
//        every { nowPlayingRepository.fetchMore() } returns loadingStateLiveData
//
//        nowPlayingUseCase.fetchMore()
//
//        verify { nowPlayingRepository.fetchMore() }
//    }

}