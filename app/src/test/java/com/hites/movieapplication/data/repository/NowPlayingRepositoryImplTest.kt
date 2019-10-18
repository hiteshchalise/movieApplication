package com.hites.movieapplication.data.repository

import com.hites.movieapplication.AppExecutors
import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.model.MoviePoster
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class NowPlayingRepositoryImplTest {

    private val resultMovie = ResultMovie(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

    private val moviePoster = MoviePoster(false, 1, "", "", 0.0)

    @Test
    fun `ResultMovie mapped to MoviePoster correctly`() {
        // When
        val mappedMoviePoster: MoviePoster = resultMovie.mapToMoviePoster()

        // Then
        assertTrue(moviePoster == mappedMoviePoster)
    }

    @Test
    fun `ResultMovie List mapped to MoviePoster List correctly`() {
        // Given
        val resultMovieList = listOf(resultMovie, resultMovie, resultMovie)
        val moviePosterList = listOf(moviePoster, moviePoster, moviePoster)

        // When
        val mappedMoviePosterList = resultMovieList.mapToMoviePosterList()

        // Then
        moviePosterList.forEachIndexed { index, it ->
            assertTrue(it == mappedMoviePosterList[index])
        }
    }

}
