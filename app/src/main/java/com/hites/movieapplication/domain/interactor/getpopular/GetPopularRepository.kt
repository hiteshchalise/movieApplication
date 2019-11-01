package com.hites.movieapplication.domain.interactor.getpopular

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.Movie

interface GetPopularRepository {
    fun getMovies(): Either<Failure, List<Movie>>

}
