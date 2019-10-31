package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.SimpleMovie
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

    override fun getNowPlaying(cached: Boolean): Either<Failure, List<MoviePoster>> {
        val movieListEither = dataSourceFactory.getDataSource(cached = cached).getNowPlaying()
        return mapMovieList(movieListEither)
    }

    fun mapMovieList(movieListEither: Either<Failure, List<SimpleMovie>>): Either<Failure, List<MoviePoster>> {
        return movieListEither.map { it.mapToMoviePosterList() }
    }
}

fun SimpleMovie.mapToMoviePoster(): MoviePoster {
    return MoviePoster(this.adult, this.id, this.posterPath, this.title, this.voteAverage)
}

fun List<SimpleMovie>.mapToMoviePosterList(): List<MoviePoster> {
    val listMoviePoster: ArrayList<MoviePoster> = ArrayList()
    this.forEach {
        listMoviePoster.add(it.mapToMoviePoster())
    }
    return listMoviePoster
}
