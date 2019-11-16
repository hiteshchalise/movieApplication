package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.NowPlayingDataSource
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.functional.map
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingRepository
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val dataSource: NowPlayingDataSource
) :
    NowPlayingRepository {

    override fun getNowPlaying(cached: Boolean): Either<Failure, List<Movie>> {
        val movieListEither = dataSource.getNowPlaying()
        return mapMovieList(movieListEither)
    }

    fun mapMovieList(nowPlayingMovieListEither: Either<Failure, List<NowPlayingMovieDTO>>): Either<Failure, List<Movie>> {
        return nowPlayingMovieListEither.map { it.mapToMovieList() }
    }
}

fun NowPlayingMovieDTO.mapToMovie(): Movie {
    return Movie(
        this.id ?: Movie.EMPTY.id,
        this.adult ?: Movie.EMPTY.adult,
        this.backdropPath ?: Movie.EMPTY.backdrop_path,
        this.originalLanguage ?: Movie.EMPTY.original_language,
        this.originalTitle ?: Movie.EMPTY.original_title,
        this.overview ?: Movie.EMPTY.overview,
        this.popularity ?: Movie.EMPTY.popularity,
        this.posterPath ?: Movie.EMPTY.poster_path,
        this.releaseDate ?: Movie.EMPTY.release_date,
        this.title ?: Movie.EMPTY.title,
        this.video ?: Movie.EMPTY.video,
        this.voteAverage ?: Movie.EMPTY.vote_average,
        this.voteCount ?: Movie.EMPTY.vote_count
    )
}

fun List<NowPlayingMovieDTO>.mapToMovieList(): List<Movie> {
    val listMovie: ArrayList<Movie> = ArrayList()
    this.forEach {
        listMovie.add(it.mapToMovie())
    }
    return listMovie
}
