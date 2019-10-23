package com.hites.movieapplication.di

import android.app.Application
import androidx.room.Room
import com.hites.movieapplication.data.datasource.local.AppDatabase
import com.hites.movieapplication.data.datasource.local.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase{
        return Room.databaseBuilder(application,
            AppDatabase::class.java, "MovieApplication.db")
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: AppDatabase): MovieDao{
        return appDatabase.movieDao()
    }
}