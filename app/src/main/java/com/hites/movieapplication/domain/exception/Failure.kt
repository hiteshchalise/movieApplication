package com.hites.movieapplication.domain.exception

sealed class Failure {

    object NetworkConnection: Failure()
    object ResponseUnSuccessful: Failure()
    object ServerError: Failure()

    /** * Extend this class for feature specific failure */
    abstract class FeatureFailure: Failure()
}