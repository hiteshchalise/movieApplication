package com.hites.movieapplication.data.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
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
    fun writeMovieListAndReadMovieList(){
        val resultMovie = NowPlayingMovieDTO(1, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)
        val resultMovie2 = NowPlayingMovieDTO(2, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)

        val resultMovieList = listOf(resultMovie, resultMovie2)

        movieDao.insertNowPlayingMovies(resultMovieList)
        val actualMovieList = movieDao.getNowPlayingMovies()

        assertEquals(resultMovieList, actualMovieList)

    }

}