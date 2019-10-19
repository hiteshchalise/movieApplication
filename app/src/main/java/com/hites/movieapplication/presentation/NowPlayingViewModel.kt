package com.hites.movieapplication.presentation

import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(movieDao: MovieDao, movieApiService: MovieApiService)