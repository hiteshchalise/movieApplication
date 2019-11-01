package com.hites.movieapplication.domain.interactor.getpopular

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularUseCase.*
import com.hites.movieapplication.domain.model.Movie

class GetPopularUseCase(private val getPopularRepository: GetPopularRepository) :
    UseCase<List<Movie>, Params>() {

    override suspend fun runs(params: Params): Either<Failure, List<Movie>> {
        return getPopularRepository.getMovies(true)
    }


    data class Params(val boolean: Boolean)
}