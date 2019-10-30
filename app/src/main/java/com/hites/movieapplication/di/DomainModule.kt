package com.hites.movieapplication.di

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.repository.NowPlayingRepositoryImpl
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providesNowPlayingRepository(dataSourceFactory: DataSourceFactory): NowPlayingRepository{
        return NowPlayingRepositoryImpl(dataSourceFactory)
    }


    @Provides
    @Singleton
    fun providesNowPlayingUseCase(nowPlayingRepository: NowPlayingRepository): NowPlayingUseCase{
        return NowPlayingUseCase(nowPlayingRepository)
    }
}