package com.hites.movieapplication.data.datasource

import android.util.Log
import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import retrofit2.Call

interface NowPlayingDataSource{
    fun getNowPlaying(cached: Boolean): Either<Failure, List<NowPlayingMovieDTO>>
}
interface GetPopularMoviesDataSource{
    fun getPopularMovies(cached: Boolean): Either<Failure, List<PopularMovieDTO>>
}
interface GetDetailsMoviesDataSource{
    fun getDetails(id: Int, cached: Boolean): Either<Failure, MovieDetailsDTO>
}

fun <T> request(
    call: Call<T>,
    default: T
): Either<Failure, T> {
    return try{
        val response = call.execute()
        Log.d("MovieApplication", "RemoteDataSource: ${response.body()}  ${response.headers()}")
        when(response.isSuccessful){
            true -> Either.Right(response.body() ?: default)
            false -> Either.Left(Failure.ResponseUnSuccessful)
        }
    } catch (exception: Throwable){
        Log.d("MovieApplication", "RemoteDataSource: $exception")
        Either.Left(Failure.ServerError)
    }
}