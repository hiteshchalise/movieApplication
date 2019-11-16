package com.hites.movieapplication.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest{

    private lateinit var movieDao: MovieDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = appDatabase.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeNowPlayingMovieListAndReadIt(){
        val nowPlayingMovie = NowPlayingMovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)
        val nowPlayingMovie2 = NowPlayingMovieDTO(2, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        val expectedNowPlayingMovieList = listOf(nowPlayingMovie, nowPlayingMovie2)

        movieDao.insertNowPlayingMovies(expectedNowPlayingMovieList)
        val actualMovieList = movieDao.getNowPlayingMovies()

        assertEquals(expectedNowPlayingMovieList, actualMovieList)

    }

    @Test
    @Throws(Exception::class)
    fun writePopularMovieListAndReadIt(){
        val popularMovie = PopularMovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)
        val popularMovie2 = PopularMovieDTO(2, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        val expectedPopularMovieList = listOf(popularMovie, popularMovie2)

        movieDao.insertPopularMovies(expectedPopularMovieList)
        val actualMovieList = movieDao.getPopularMovies()

        assertEquals(expectedPopularMovieList, actualMovieList)

    }

    @Test
    @Throws(Exception::class)
    fun writeMovieDetailsAndReadIt(){
        val movieDetailsDto = MovieDetailsDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        movieDao.insertMovieDetails(movieDetailsDto)
        val actualMovieDetail = movieDao.getMovieDetails(1)

        assertEquals(movieDetailsDto, actualMovieDetail)

    }

    @Test
    @Throws(Exception::class)
    fun deletesNowPlayingList(){
        // Given
        val nowPlayingMovie = NowPlayingMovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        val nowPlayingMovieList = listOf(nowPlayingMovie, nowPlayingMovie)
        movieDao.insertNowPlayingMovies(nowPlayingMovieList)

        // When
        movieDao.removeNowPlayingMovies()

        // Then
        assertEquals(emptyList<NowPlayingMovieDTO>(), movieDao.getNowPlayingMovies())
    }

    @Test
    @Throws(Exception::class)
    fun deletesPopularMovieList(){
        // Given
        val popularMovie = PopularMovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        val popularMovieList = listOf(popularMovie, popularMovie)
        movieDao.insertPopularMovies(popularMovieList)

        // When
        movieDao.removePopularMovies()

        // Then
        assertEquals(emptyList<PopularMovieDTO>(), movieDao.getPopularMovies())
    }

    @Test
    @Throws(Exception::class)
    fun replacesWhenIdIsSameInDetails(){
        // Given
        val firstMovieDetailsDTO = MovieDetailsDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        val secondMovieDetailsDTO =
            MovieDetailsDTO(1, true, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        // When
        movieDao.insertMovieDetails(firstMovieDetailsDTO)
        movieDao.insertMovieDetails(secondMovieDetailsDTO)

        // Then
        assertEquals(secondMovieDetailsDTO, movieDao.getMovieDetails(1))
    }

}