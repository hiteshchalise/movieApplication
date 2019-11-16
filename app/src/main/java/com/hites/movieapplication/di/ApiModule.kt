package com.hites.movieapplication.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hites.movieapplication.data.datasource.remote.EnvelopingConverter
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {


    @Provides
    @Singleton
    internal fun provideGson(): Gson{
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache{
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache) :OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)

        return httpClient.build()
    }

    @Provides
    @Singleton
    @Named("EnvelopingApi")
    internal fun provideEnvelopedRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(EnvelopingConverter())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("NonEnvelopingApi")
    internal fun provideNonEnvelopedRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("Enveloped")
    internal fun provideMovieApiService(@Named("EnvelopingApi")retrofit: Retrofit): MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("NonEnveloped")
    internal fun provideMovieApiServiceNonEnvelope(@Named("NonEnvelopingApi")retrofit: Retrofit): MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }

}
