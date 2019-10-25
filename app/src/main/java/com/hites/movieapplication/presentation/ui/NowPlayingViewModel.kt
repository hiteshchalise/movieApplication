package com.hites.movieapplication.presentation.ui

import android.util.Log
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
        Log.d("MovieApplication", "here")
        nowPlayingUseCase.execute(NowPlayingListObserver(), null)
    }

    fun getNowPlayingListLiveData() = liveDataNowPlayingList

    inner class NowPlayingListObserver: DefaultObserver<List<MoviePoster>>(){
        override fun onNext(t: List<MoviePoster>) {
            liveDataNowPlayingList.value = t
        }

        override fun onError(e: Throwable) {
            Log.d("MovieApplication", "Error: ", e)
        }

        override fun onComplete() {

        }

    }
}