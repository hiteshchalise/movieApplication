package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.datasource.local.LocalDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import com.hites.movieapplication.data.datasource.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceFactory @Inject constructor(private val movieApiService: MovieApiService,
                                            private val movieDao: MovieDao){

    private var lastUpdateTime = System.currentTimeMillis()
    private var firstLoading = true

    fun getDataSource(time: Int = getTimeDifference()): DataSource {
        return if ((time > 10) or firstLoading){
            firstLoading = false
            RemoteDataSource(movieApiService, movieDao)
        } else{
//            LocalDataSource(movieDao)
            RemoteDataSource(movieApiService, movieDao)
        }
    }

    private fun getTimeDifference(): Int {
        // This returns difference in minutes
        val inMinutes = ((lastUpdateTime - System.currentTimeMillis()) * 1000 * 60).toInt()
        lastUpdateTime = System.currentTimeMillis()
        return inMinutes
    }

}