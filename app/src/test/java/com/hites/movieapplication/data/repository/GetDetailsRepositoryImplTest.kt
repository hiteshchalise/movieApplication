package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.getdetails.GetDetailsRepository
import com.hites.movieapplication.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.full.isSubclassOf

class GetDetailsRepositoryImplTest{

    private val movieDetailsDTO = MovieDetailsDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

    private val movieDomain = Movie(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

    private val dataSourceFactory: DataSourceFactory = mockk()
    private val getDetailsRepository = spyk(GetDetailsRepositoryImpl(dataSourceFactory))

    @Test
    fun `GetDetailsRepositoryImpl extends GetDetailsRepository`() {
        assertTrue(GetDetailsRepositoryImpl::class.isSubclassOf(GetDetailsRepository::class))
    }

    @Test
    fun `MovieDetailsDTO mapped to Movie correctly`() {
        // When
        val mappedMovieDetails: Movie = movieDetailsDTO.mapToMovie()

        // Then
        assertTrue(movieDomain == mappedMovieDetails)
    }

    @Test
    fun `When DataSource Returns MovieDetailsDTO`() {
        // Given
        val movieDetailsDTORight = Either.Right(movieDetailsDTO)
        val movieDomainRight = Either.Right(movieDomain)
        every { dataSourceFactory.getDataSource(true).getDetails(0) } returns movieDetailsDTORight
        every { getDetailsRepository.mapMovie(movieDetailsDTORight) } returns movieDomainRight

        // When
        val movieDetailsDTO = getDetailsRepository.getDetails(true, 0)

        // Then
        verify { getDetailsRepository.getDetails(true, 0) }

        assertNotNull(movieDetailsDTO)
        assertEquals(
            movieDomainRight,
            movieDetailsDTO
        )

    }

    @Test
    fun `When DataSource Returns Failure`(){
        // Given
        val failure = Either.Left(Failure.ServerError)
        every { dataSourceFactory.getDataSource(true).getDetails(0) } returns failure
        every { getDetailsRepository.mapMovie(failure) } returns failure

        // When
        val movieDetailsDTOEither = getDetailsRepository.getDetails(true, 0)

        // Then
        verify { getDetailsRepository.getDetails(true, 0) }
        assertNotNull(movieDetailsDTOEither)
        assertEquals(
            failure, movieDetailsDTOEither
        )
    }


    @Test
    fun `Map MovieList Works For EitherLeft`() {
        // Given
        val failure = Either.Left(Failure.ServerError)

        // When
        val mapMovieEitherActual = getDetailsRepository.mapMovie(failure)

        // Then
        verify { getDetailsRepository.mapMovie(failure) }
        assertEquals(failure, mapMovieEitherActual)
    }

    @Test
    fun `Map MovieList Works For EitherRight`() {
        // Given
        val movieDetailsDTOEither = Either.Right(
            movieDetailsDTO
        )
        val movieDomainList = Either.Right(
            movieDomain
        )

        // When
        val mapMovieEitherActual = getDetailsRepository.mapMovie(movieDetailsDTOEither)

        // Then
        verify { getDetailsRepository.mapMovie(movieDetailsDTOEither) }
        assertEquals(movieDomainList, mapMovieEitherActual)
    }

}