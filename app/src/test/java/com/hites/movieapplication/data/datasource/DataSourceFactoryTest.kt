package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.datasource.local.LocalDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import com.hites.movieapplication.data.datasource.remote.RemoteDataSource
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class DataSourceFactoryTest {
    private val movieApiService: MovieApiService = mockk()
    private val movieDao: MovieDao = mockk()
    private val dataSourceFactory = DataSourceFactory(movieApiService, movieDao)

    @Test
    fun `DataSourceFactory should return LocalDataSource`() {
        // When
        val dataSource = dataSourceFactory.getDataSource(true)
        // Then
        assertTrue(dataSource is LocalDataSource)
    }

    @Test
    fun `DataSourceFactory should return RemoteDataSource`() {
        // When
        val dataSource = dataSourceFactory.getDataSource(false)
        // Then
        assertTrue(dataSource is RemoteDataSource)
    }
}