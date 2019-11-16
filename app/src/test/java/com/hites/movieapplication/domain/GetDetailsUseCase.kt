package com.hites.movieapplication.domain

import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.interactor.getdetails.GetDetailsRepository
import com.hites.movieapplication.domain.interactor.getdetails.GetDetailsUseCase
import com.hites.movieapplication.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.full.isSubclassOf

class GetDetailsUseCase {

    val getDetailsRepository: GetDetailsRepository = mockk()
    val getDetailsUseCase = GetDetailsUseCase(getDetailsRepository)
    private val movie: Movie = mockk()

    @Test
    fun `GetDetailsUseCase extends UseCase`() {
        assertTrue(GetDetailsUseCase::class.isSubclassOf(UseCase::class))
    }

    @Test
    fun `GetDetailsRepository Returns Properly`() {
        // Given
        val expected = Either.Right(movie)
        every { getDetailsRepository.getDetails(true, 0) } returns expected

        // When
        runBlocking { val actual = getDetailsUseCase.runs(GetDetailsUseCase.Params(true, 0))
            // Then
            assertEquals(expected, actual)
        }

        // Then
        verify { getDetailsRepository.getDetails(true, 0) }
    }
}