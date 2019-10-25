package com.hites.movieapplication.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import com.hites.movieapplication.domain.model.MoviePoster
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {

    val liveDataNowPlayingList = MutableLiveData<List<MoviePoster>>()
    val failureLiveData = MutableLiveData<Failure>()

    fun loadMoreMovies() {
        nowPlayingUseCase.execute({it.either(::handleFailure, ::handleNowPlayingList)}, null)
    }

    private fun handleNowPlayingList(nowPlayingList: List<MoviePoster>){
        this.liveDataNowPlayingList.value = nowPlayingList
    }

    private fun handleFailure(failure: Failure){
        this.failureLiveData.value = failure
    }

}