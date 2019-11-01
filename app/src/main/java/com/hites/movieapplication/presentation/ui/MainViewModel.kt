package com.hites.movieapplication.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hites.movieapplication.domain.exception.Failure
import com.hites.movieapplication.domain.interactor.getpopular.GetPopularUseCase
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import com.hites.movieapplication.domain.model.Movie
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase,
private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    val liveDataNowPlayingList = MutableLiveData<List<Movie>>()
    val failureNowPlayingLiveData = MutableLiveData<Failure>()

    val liveDataPopularMovieList= MutableLiveData<List<Movie>>()
    val failureGetPopularLiveData = MutableLiveData<Failure>()

    fun loadNowPlayingList(cached: Boolean) {
        nowPlayingUseCase.execute(
            { it.either(::handleNowPlayingFailure, ::handleNowPlayingList) },
            NowPlayingUseCase.Params(cached)
        )
    }

    fun loadPopularList(cached: Boolean) {
        getPopularUseCase.execute(
            { it.either(::handlePopularFailure, ::handlePopularList) },
            GetPopularUseCase.Params(cached)
        )
    }

    private fun handlePopularList(popularList: List<Movie>) {
        this.liveDataPopularMovieList.value = popularList
    }

    private fun handlePopularFailure(failure: Failure) {
        this.failureGetPopularLiveData.value = failure
    }

    private fun handleNowPlayingList(nowPlayingList: List<Movie>){
        this.liveDataNowPlayingList.value = nowPlayingList
    }

    private fun handleNowPlayingFailure(failure: Failure){
        this.failureNowPlayingLiveData.value = failure
    }

}