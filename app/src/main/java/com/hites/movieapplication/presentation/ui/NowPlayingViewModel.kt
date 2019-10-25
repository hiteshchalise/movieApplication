package com.hites.movieapplication.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hites.movieapplication.domain.interactor.DefaultObserver
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import com.hites.movieapplication.domain.model.MoviePoster
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {

    private val liveDataNowPlayingList = MutableLiveData<List<MoviePoster>>()

    fun loadMoreMovies() {
        nowPlayingUseCase.execute(NowPlayingListObserver(), null)
    }

    fun getNowPlayingListLiveData() = liveDataNowPlayingList

    class NowPlayingListObserver: DefaultObserver<List<MoviePoster>>(){
        override fun onNext(t: List<MoviePoster>) {
            TODO("not implemented")
        }

        override fun onError(e: Throwable) {
            TODO("not implemented")
        }

        override fun onComplete() {
            TODO("not implemented")
        }

    }
}