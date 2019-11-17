package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.GetPopularMoviesDataSource
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.functional.map
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularRepository
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class GetPopularRepositoryImpl @Inject constructor(
    private val dataSource: GetPopularMoviesDataSource
) : GetPopularRepository {

    override fun getMovies(cached: Boolean): Either<Failure, List<Movie>> {
        val popularMovies = dataSource.getPopularMovies(cached)
        return mapPopularMoviesDTO(popularMovies)
    }

    fun mapPopularMoviesDTO(popularMovies: Either<Failure, List<PopularMovieDTO>>): Either<Failure, List<Movie>> {
        return popularMovies.map {
            it.mapToMovieList()
        }
    }

}
fun PopularMovieDTO.mapToMovie(): Movie {
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
    )}

fun List<PopularMovieDTO>.mapToMovieList(): List<Movie> {
    val listMovie: ArrayList<Movie> = ArrayList()
    this.forEach {
        listMovie.add(it.mapToMovie())
    }
    return listMovie
}
