package com.hites.movieapplication.presentation.ui.detailsactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.interactor.getdetails.GetDetailsUseCase
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    val movieDetailsLiveData= MutableLiveData<Movie>()
    val failureMovieDetailsLiveData = MutableLiveData<Failure>()


    fun loadNowPlayingList(cached: Boolean, id: Int) {
        getDetailsUseCase.execute(
            { it.either(::handleNowPlayingFailure, ::handleNowPlayingList) },
            GetDetailsUseCase.Params(cached, id)
        )
    }

    private fun handleNowPlayingList(movie: Movie) {
        movieDetailsLiveData.value = movie
    }

    private fun handleNowPlayingFailure(failure: Failure){
        failureMovieDetailsLiveData.value = failure
    }
}