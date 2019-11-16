package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.GetDetailsMoviesDataSource
import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.functional.map
import com.hites.movieapplication.domain.interactor.getdetails.GetDetailsRepository
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class GetDetailsRepositoryImpl @Inject constructor(
    private val dataSource: GetDetailsMoviesDataSource
) : GetDetailsRepository {
    override fun getDetails(boolean: Boolean, id: Int): Either<Failure, Movie> {
        val movieDetailsEither =  dataSource.getDetails(id)
        return mapMovie(movieDetailsEither)
    }
    fun mapMovie(movieDetailsEither: Either<Failure, MovieDetailsDTO>): Either<Failure, Movie> {
        return movieDetailsEither.map { it.mapToMovie() }
    }

}
fun MovieDetailsDTO.mapToMovie(): Movie {
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
