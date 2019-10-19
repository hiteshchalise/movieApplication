package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.datasource.local.LocalDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import com.hites.movieapplication.data.datasource.remote.RemoteDataSource
import javax.inject.Singleton

@Singleton
class DataSourceFactory(private val movieApiService: MovieApiService,
                        private val movieDao: MovieDao){

    var lastUpdateTime = System.currentTimeMillis()

    fun getDataSource(time: Int = getTimeDifference()): DataSource {
        return if (time > 10){
            RemoteDataSource(movieApiService, movieDao)
        } else{
            LocalDataSource(movieDao)
        }
    }

    private fun getTimeDifference(): Int {
        // This returns difference in minutes
        val inMinutes = ((lastUpdateTime - System.currentTimeMillis()) * 1000 * 60).toInt()
        lastUpdateTime = System.currentTimeMillis()
        return inMinutes
    }

}