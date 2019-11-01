package com.hites.movieapplication.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {

    val liveDataNowPlayingList = MutableLiveData<List<Movie>>()
    val failureLiveData = MutableLiveData<Failure>()

    fun loadCachedMovies() {
        nowPlayingUseCase.execute({it.either(::handleFailure, ::handleNowPlayingList)},
            NowPlayingUseCase.Params(true))
    }

    fun loadFreshMovies(){
        nowPlayingUseCase.execute({it.either(::handleFailure, ::handleNowPlayingList)},
            NowPlayingUseCase.Params(false))
    }

    private fun handleNowPlayingList(nowPlayingList: List<Movie>){
        this.liveDataNowPlayingList.value = nowPlayingList
    }

    private fun handleFailure(failure: Failure){
        this.failureLiveData.value = failure
    }

}