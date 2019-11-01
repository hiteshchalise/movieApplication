package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularRepository
import com.hites.movieapplication.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.full.isSubclassOf

class GetPopularRepositoryImplTest{

    private val popularMovieDTO = PopularMovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

    private val movieDomain = Movie(false, 1, "", "", 0.0)

    private val dataSourceFactory: DataSourceFactory = mockk()
    private val getPopularRepository = spyk(GetPopularRepositoryImpl(dataSourceFactory))

    @Test
    fun `GetPopularRepositoryImpl extends GetPopularRepository`() {
        assertTrue(GetPopularRepositoryImpl::class.isSubclassOf(GetPopularRepository::class))
    }

    @Test
    fun `PopularMovieDTO mapped to Movie correctly`() {
        // When
        val mappedMovie: Movie = popularMovieDTO.mapToMovie()

        // Then
        assertTrue(movieDomain == mappedMovie)
    }

    @Test
    fun `PopularMovieDTO List mapped to Movie List correctly`() {
        // Given
        val popularMovieDTOList = listOf(popularMovieDTO, popularMovieDTO, popularMovieDTO)
        val movieDomainList = listOf(movieDomain, movieDomain, movieDomain)

        // When
        val mappedMovieList = popularMovieDTOList.mapToMovieList()

        // Then
        movieDomainList.forEachIndexed { index, it ->
            assertTrue(it == mappedMovieList[index])
        }
    }


    @Test
    fun `When DataSource Returns List of PopularMovieDTO`() {
        // Given
        val popularMovieDTOList = Either.Right(
            listOf(popularMovieDTO, popularMovieDTO, popularMovieDTO)
        )
        val movieDomain = Either.Right(
            listOf(movieDomain, movieDomain, movieDomain)
        )
        every { dataSourceFactory.getDataSource(true).getPopularMovies() } returns popularMovieDTOList
        every { getPopularRepository.mapPopularMoviesDTO(popularMovieDTOList) } returns movieDomain

        // When
        val popularMovies = getPopularRepository.getMovies(true)

        // Then
        verify { getPopularRepository.getMovies(true) }

        assertNotNull(popularMovies)
        assertEquals(
            movieDomain,
            popularMovies
        )

    }

    @Test
    fun `When DataSource Returns Failure`(){
        // Given
        val failure = Either.Left(Failure.ServerError)
        every { dataSourceFactory.getDataSource(true).getPopularMovies() } returns failure
        every { getPopularRepository.mapPopularMoviesDTO(failure) } returns failure

        // When
        val nowPlayingMovieEither = getPopularRepository.getMovies(true)

        // Then
        verify { getPopularRepository.getMovies(true) }
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
        val mapMovieEitherActual = getPopularRepository.mapPopularMoviesDTO(failure)

        // Then
        verify { getPopularRepository.mapPopularMoviesDTO(failure) }
        assertEquals(failure, mapMovieEitherActual)
    }

    @Test
    fun `Map MovieList Works For EitherRight`() {
        // Given
        val popularMovieDTOList = Either.Right(
            listOf(popularMovieDTO, popularMovieDTO, popularMovieDTO)
        )
        val movieDomainList = Either.Right(
            listOf(movieDomain, movieDomain, movieDomain)
        )

        // When
        val mapMovieEitherActual = getPopularRepository.mapPopularMoviesDTO(popularMovieDTOList)

        // Then
        verify { getPopularRepository.mapPopularMoviesDTO(popularMovieDTOList) }
        assertEquals(movieDomainList, mapMovieEitherActual)
    }

}