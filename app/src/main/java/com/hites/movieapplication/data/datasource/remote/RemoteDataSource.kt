package com.hites.movieapplication.data.datasource.remote

import android.util.Log
import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.model.MovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : DataSource {

    override fun getNowPlaying(): Either<Failure, List<MovieDTO>> {
        val responseEither = request(apiService.fetchNowPlaying(), emptyList())
        responseEither.either({}, {
            movieDao.insertMovies(it)
        })
        return responseEither
    }

    private fun request(
        call: Call<List<MovieDTO>>,
        default: List<MovieDTO>
    ): Either<Failure, List<MovieDTO>> {
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