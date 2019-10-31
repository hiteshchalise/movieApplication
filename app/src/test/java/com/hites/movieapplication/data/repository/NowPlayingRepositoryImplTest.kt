package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.SimpleMovie
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.MoviePoster
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.*
import org.junit.Test

class NowPlayingRepositoryImplTest {

    private val simpleMovie = SimpleMovie(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

    private val moviePoster = MoviePoster(false, 1, "", "", 0.0)

    private val dataSourceFactory: DataSourceFactory = mockk()
    private val nowPlayingRepository = spyk(NowPlayingRepositoryImpl(dataSourceFactory))

    @Test
    fun `ResultMovie mapped to MoviePoster correctly`() {
        // When
        val mappedMoviePoster: MoviePoster = simpleMovie.mapToMoviePoster()

        // Then
        assertTrue(moviePoster == mappedMoviePoster)
    }

    @Test
    fun `ResultMovie List mapped to MoviePoster List correctly`() {
        // Given
        val simpleMovieList = listOf(simpleMovie, simpleMovie, simpleMovie)
        val moviePosterList = listOf(moviePoster, moviePoster, moviePoster)

        // When
        val mappedMoviePosterList = simpleMovieList.mapToMoviePosterList()

        // Then
        moviePosterList.forEachIndexed { index, it ->
            assertTrue(it == mappedMoviePosterList[index])
        }
    }


    @Test
    fun `When DataSource Returns List of SimpleMovie`() {
        // Given
        val rightSimpleMovie = Either.Right(
            listOf(simpleMovie, simpleMovie, simpleMovie)
        )
        val rightMoviePoster = Either.Right(
            listOf(moviePoster, moviePoster, moviePoster)
        )
        every { dataSourceFactory.getDataSource(true).getNowPlaying() } returns rightSimpleMovie
        every { nowPlayingRepository.mapMovieList(rightSimpleMovie) } returns rightMoviePoster

        // When
        val nowPlayingMovieEither = nowPlayingRepository.getNowPlaying(true)

        // Then
        verify { nowPlayingRepository.getNowPlaying(true) }

        assertNotNull(nowPlayingMovieEither)
        assertEquals(
            rightMoviePoster,
            nowPlayingMovieEither
        )

    }

    @Test
    fun `When DataSource Returns Failure`(){
        // Given
        val failure = Either.Left(Failure.ServerError)
        every { dataSourceFactory.getDataSource(true).getNowPlaying() } returns failure
        every { nowPlayingRepository.mapMovieList(failure) } returns failure

        // When
        val nowPlayingMovieEither = nowPlayingRepository.getNowPlaying(true)

        // Then
        verify { nowPlayingRepository.getNowPlaying(true) }
        assertNotNull(nowPlayingMovieEither)
        assertEquals(
            failure, nowPlayingMovieEither
        )
    }


    @Test
    fun `Map MovieList Works For EitherLeft`() {
        // Given
        val failure = Either.Left(Failure.ServerError)

        // When
        val mapMovieEitherActual = nowPlayingRepository.mapMovieList(failure)

        // Then
        verify { nowPlayingRepository.mapMovieList(failure) }
        assertEquals(failure, mapMovieEitherActual)
    }

    @Test
    fun `Map MovieList Works For EitherRight`() {
        // Given
        val rightSimpleMovie = Either.Right(
            listOf(simpleMovie, simpleMovie, simpleMovie)
        )
        val rightMoviePoster = Either.Right(
            listOf(moviePoster, moviePoster, moviePoster)
        )

        // When
        val mapMovieEitherActual = nowPlayingRepository.mapMovieList(rightSimpleMovie)

        // Then
        verify { nowPlayingRepository.mapMovieList(rightSimpleMovie) }
        assertEquals(rightMoviePoster, mapMovieEitherActual)
    }

}
