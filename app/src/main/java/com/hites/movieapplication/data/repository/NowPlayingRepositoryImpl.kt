package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.MovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.functional.map
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) :
    NowPlayingRepository {

    override fun getNowPlaying(cached: Boolean): Either<Failure, List<Movie>> {
        val movieListEither = dataSourceFactory.getDataSource(cached = cached).getNowPlaying()
        return mapMovieList(movieListEither)
    }

    fun mapMovieList(movieListEither: Either<Failure, List<MovieDTO>>): Either<Failure, List<Movie>> {
        return movieListEither.map { it.mapToMoviePosterList() }
    }
}

fun MovieDTO.mapToMoviePoster(): Movie {
    return Movie(
        this.adult ?: Movie.EMPTY.adult,
        this.id ?: Movie.EMPTY.id,
        this.posterPath ?: Movie.EMPTY.poster_path ,
        this.title ?: Movie.EMPTY.title,
        this.voteAverage ?: Movie.EMPTY.vote_average
    )
}

fun List<MovieDTO>.mapToMoviePosterList(): List<Movie> {
    val listMovie: ArrayList<Movie> = ArrayList()
    this.forEach {
        listMovie.add(it.mapToMoviePoster())
    }
    return listMovie
}
