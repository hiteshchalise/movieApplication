package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.MovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.*
import org.junit.Test

class NowPlayingRepositoryImplTest {

    private val remoteMovieDTO = MovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

    private val moviePoster = Movie(false, 1, "", "", 0.0)

    private val dataSourceFactory: DataSourceFactory = mockk()
    private val nowPlayingRepository = spyk(NowPlayingRepositoryImpl(dataSourceFactory))

    @Test
    fun `ResultMovie mapped to MoviePoster correctly`() {
        // When
        val mappedMovie: Movie = remoteMovieDTO.mapToMoviePoster()

        // Then
        assertTrue(moviePoster == mappedMovie)
    }

    @Test
    fun `ResultMovie List mapped to MoviePoster List correctly`() {
        // Given
        val remoteMovieDTOList = listOf(remoteMovieDTO, remoteMovieDTO, remoteMovieDTO)
        val moviePosterList = listOf(moviePoster, moviePoster, moviePoster)

        // When
        val mappedMoviePosterList = remoteMovieDTOList.mapToMoviePosterList()

        // Then
        moviePosterList.forEachIndexed { index, it ->
            assertTrue(it == mappedMoviePosterList[index])
        }
    }


    @Test
    fun `When DataSource Returns List of SimpleMovie`() {
        // Given
        val rightSimpleMovie = Either.Right(
            listOf(remoteMovieDTO, remoteMovieDTO, remoteMovieDTO)
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
            listOf(remoteMovieDTO, remoteMovieDTO, remoteMovieDTO)
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
