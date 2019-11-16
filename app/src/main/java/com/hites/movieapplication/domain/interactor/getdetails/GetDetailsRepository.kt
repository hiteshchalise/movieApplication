package com.hites.movieapplication.domain.interactor.getdetails

import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.functional.Either
import com.hites.movieapplication.domain.model.Movie

interface GetDetailsRepository {
    fun getDetails(boolean: Boolean, id: Int): Either<Failure, Movie>
}