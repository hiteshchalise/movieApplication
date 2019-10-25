package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.ResultMovie
import com.hites.movieapplication.domain.model.MoviePoster
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import io.reactivex.Observable
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) :
    NowPlayingRepository {

    override fun getNowPlaying(): Observable<List<MoviePoster>> {
        return dataSourceFactory.getDataSource().getNowPlaying().map {
            it.mapToMoviePosterList()
        }
    }
}

fun ResultMovie.mapToMoviePoster(): MoviePoster {
    return MoviePoster(this.adult, this.id, this.posterPath, this.title, this.voteAverage)
}

fun List<ResultMovie>.mapToMoviePosterList(): List<MoviePoster> {
    val listMoviePoster: ArrayList<MoviePoster> = ArrayList()
    this.forEach {
        listMoviePoster.add(it.mapToMoviePoster())
    }
    return listMoviePoster
}
