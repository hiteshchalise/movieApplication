package com.hites.movieapplication.domain

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.MoviePoster
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NowPlayingUseCaseTest {
    private val nowPlayingRepository: NowPlayingRepository = mockk()
    private val nowPlayingUseCase =
        NowPlayingUseCase(
            nowPlayingRepository
        )
    private val moviePoster: MoviePoster = mockk()

    @Test
    fun `repository returns success`() {
        // Given
        val rightMoviePoster = Either.Right(
            listOf(
                moviePoster, moviePoster, moviePoster
            )
        )
        val params = NowPlayingUseCase.Params(true)
        every { nowPlayingRepository.getNowPlaying(true) } returns rightMoviePoster

//        // When
        val nowPlayingActual =
            runBlocking { nowPlayingUseCase.runs(params) }
//
//        // Then
        verify{ runBlocking { nowPlayingUseCase.runs(params)}}
        assertEquals(rightMoviePoster, nowPlayingActual)
    }

    @Test
    fun `repository returns failure`() {
        // Given
        val leftFailure = Either.Left(
            Failure.ServerError
        )
        every { nowPlayingRepository.getNowPlaying(true) } returns leftFailure
        val params = NowPlayingUseCase.Params(true)

//        // When
        val nowPlayingActual = runBlocking { nowPlayingUseCase.runs(params) }

//        // Then
        verify{ runBlocking { nowPlayingUseCase.runs(params)}}
        assertEquals(leftFailure, nowPlayingActual)
    }

}