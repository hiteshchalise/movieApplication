package com.hites.movieapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import com.hites.movieapplication.domain.interactor.nowplaying.NowPlayingUseCase
import com.hites.movieapplication.domain.model.MoviePoster
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val liveDataNowPlayingList = MutableLiveData<List<MoviePoster>>()

    fun loadMoreMovies() {
        val subscription = nowPlayingUseCase.buildUseCaseObservable(null)
            .subscribe {
                liveDataNowPlayingList.postValue(it)
            }
        addDisposable(subscription)
    }

    private fun addDisposable(subscription: Disposable) {
        compositeDisposable.add(subscription)
    }

    private fun getMutableLiveData() = liveDataNowPlayingList

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}