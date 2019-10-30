package com.hites.movieapplication.data.repository

import android.util.Log
import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.functional.map
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.model.MoviePoster
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) :
    NowPlayingRepository {

    override fun getNowPlaying(): Either<Failure, List<MoviePoster>> {
        val movieList = dataSourceFactory.getDataSource().getNowPlaying()
        Log.d("MovieApplication", "NowPlayingRepositoryImpl: $movieList")
        return movieList.map { it.mapToMoviePosterList() }
    }
}

fun Movie.mapToMoviePoster(): MoviePoster {
    return MoviePoster(this.adult, this.id, this.posterPath, this.title, this.voteAverage)
}

fun List<Movie>.mapToMoviePosterList(): List<MoviePoster> {
    val listMoviePoster: ArrayList<MoviePoster> = ArrayList()
    this.forEach {
        listMoviePoster.add(it.mapToMoviePoster())
    }
    return listMoviePoster
}
