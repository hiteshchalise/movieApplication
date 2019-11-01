package com.hites.movieapplication.data.repository

import com.hites.movieapplication.data.datasource.DataSourceFactory
import com.hites.movieapplication.data.model.PopularMovieDTO
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.functional.map
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularRepository
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class GetPopularRepositoryImpl @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) : GetPopularRepository {

    override fun getMovies(cached: Boolean): Either<Failure, List<Movie>> {
        val popularMovies = dataSourceFactory.getDataSource(cached).getPopularMovies()
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
        this.adult ?: Movie.EMPTY.adult,
        this.id ?: Movie.EMPTY.id,
        this.posterPath ?: Movie.EMPTY.poster_path ,
        this.title ?: Movie.EMPTY.title,
        this.voteAverage ?: Movie.EMPTY.vote_average
    )
}

fun List<PopularMovieDTO>.mapToMovieList(): List<Movie> {
    val listMovie: ArrayList<Movie> = ArrayList()
    this.forEach {
        listMovie.add(it.mapToMovie())
    }
    return listMovie
}
