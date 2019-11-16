package com.hites.movieapplication.domain.interactor.getdetails

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.interactor.UseCase
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor (private val getDetailsRepository: GetDetailsRepository) :
    UseCase<Movie, GetDetailsUseCase.Params>() {

    override suspend fun runs(params: Params): Either<Failure, Movie> {
        return getDetailsRepository.getDetails(params.boolean, params.id)
    }


    data class Params(val boolean: Boolean, val id: Int)
}