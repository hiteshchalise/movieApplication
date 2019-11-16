package com.hites.movieapplication.di

import com.hites.movieapplication.data.datasource.GetDetailsMoviesDataSource
import com.hites.movieapplication.data.datasource.GetPopularMoviesDataSource
import com.hites.movieapplication.data.datasource.NowPlayingDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import com.hites.movieapplication.data.datasource.remote.RemoteGetDetails
import com.hites.movieapplication.data.datasource.remote.RemoteGetPopularMovies
import com.hites.movieapplication.data.datasource.remote.RemoteNowPlaying
import com.hites.movieapplication.data.repository.GetDetailsRepositoryImpl
import com.hites.movieapplication.data.repository.GetPopularRepositoryImpl
import com.hites.movieapplication.data.repository.NowPlayingRepositoryImpl
import com.hites.movieapplication.domain.interactor.getdetails.GetDetailsRepository
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularRepository
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun providesNowPlayingDataSource(@Named("Enveloped") movieApiService: MovieApiService, movieDao: MovieDao): NowPlayingDataSource{
        return RemoteNowPlaying(movieApiService, movieDao)
    }

    @Provides
    @Singleton
    fun providesGetPopularMoviesDataSource(@Named("Enveloped") movieApiService: MovieApiService, movieDao: MovieDao): GetPopularMoviesDataSource{
        return RemoteGetPopularMovies(movieApiService, movieDao)
    }

    @Provides
    @Singleton
    fun providesGetDetailsMoviesDataSource(@Named("NonEnveloped")movieApiService: MovieApiService, movieDao: MovieDao): GetDetailsMoviesDataSource{
        return RemoteGetDetails(movieApiService, movieDao)
    }

    @Provides
    @Singleton
    fun providesNowPlayingRepository(nowPlayingDataSource: NowPlayingDataSource): NowPlayingRepository{
        return NowPlayingRepositoryImpl(nowPlayingDataSource)
    }

    @Provides
    @Singleton
    fun providesGetPopularRepository(getPopularMoviesDataSource: GetPopularMoviesDataSource): GetPopularRepository{
        return GetPopularRepositoryImpl(getPopularMoviesDataSource)
    }

    @Provides
    @Singleton
    fun providesGetDetailsRepository(getDetailsMoviesDataSource: GetDetailsMoviesDataSource): GetDetailsRepository {
        return GetDetailsRepositoryImpl(getDetailsMoviesDataSource)
    }
}