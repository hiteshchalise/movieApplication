package com.hites.movieapplication.di

import androidx.annotation.UiThread
import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.datasource.executor.JobExecutor
import com.hites.movieapplication.data.repository.NowPlayingRepositoryImpl
import com.hites.movieapplication.domain.executor.PostExecutionThread
import com.hites.movieapplication.domain.executor.ThreadExecutor
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import com.hites.movieapplication.presentation.UIThread
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
    fun providesThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor{
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providesPostExecutionThread(uiThread: UIThread): PostExecutionThread{
        return uiThread
    }

    @Provides
    @Singleton
    fun providesNowPlayingUseCase(nowPlayingRepository: NowPlayingRepository,
                                  threadExecutor: ThreadExecutor,
                                  postExecutionThread: PostExecutionThread): NowPlayingUseCase{
        val nowPlayingUseCase = NowPlayingUseCase(nowPlayingRepository, threadExecutor, postExecutionThread)
        nowPlayingUseCase.buildUseCaseObservable(null)
        return nowPlayingUseCase
    }
}