package com.hites.movieapplication.data.datasource.remote

import android.util.Log
import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : DataSource {
    override fun getPopularMovies(): Either<Failure, List<PopularMovieDTO>> {
        val responseEither = request(apiService.fetchPopularMovies(), emptyList())
        responseEither.either({}, {
            movieDao.insertPopularMovies(it)
        })
        return responseEither
    }

    override fun getNowPlaying(): Either<Failure, List<NowPlayingMovieDTO>> {
        val responseEither = request(apiService.fetchNowPlaying(), emptyList())
        responseEither.either({}, {
            movieDao.insertNowPlayingMovies(it)
        })
        return responseEither
    }

    private fun <T> request(
        call: Call<T>,
        default: T
    ): Either<Failure, T> {
        return try{
            val response = call.execute()
            Log.d("MovieApplication", "RemoteDataSource: ${response.body()}")
            when(response.isSuccessful){
                true -> Either.Right(response.body() ?: default)
                false -> Either.Left(Failure.ResponseUnSuccessful)
            }
        } catch (exception: Throwable){
            Log.d("MovieApplication", "RemoteDataSource: $exception")
            Either.Left(Failure.ServerError)
        }
    }

}