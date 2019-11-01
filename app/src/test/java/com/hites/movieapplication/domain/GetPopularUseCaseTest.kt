package com.hites.movieapplication.domain

import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularRepository
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularUseCase
import com.hites.movieapplication.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlin.math.acos
import kotlin.reflect.full.isSubclassOf

class GetPopularUseCaseTest {

    val getPopularRepository: GetPopularRepository = mockk()
    val getPopularUseCase = GetPopularUseCase(getPopularRepository)
    private val movie: Movie = mockk()

    @Test
    fun `GetPopularUseCase extends UseCase`() {
        assertTrue(GetPopularUseCase::class.isSubclassOf(UseCase::class))
    }

    @Test
    fun `GetPopularRepository Returns Properly`() {
        // Given
        val listOfMovie = listOf(movie, movie, movie)
        val expected = Either.Right(listOfMovie)
        every { getPopularRepository.getMovies() } returns expected

        // When
        runBlocking { val actual = getPopularUseCase.runs(GetPopularUseCase.Params(true))
            // Then
            assertEquals(expected, actual)
        }

        // Then
        verify { getPopularRepository.getMovies() }
    }
}