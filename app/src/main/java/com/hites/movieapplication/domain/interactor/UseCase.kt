package com.hites.movieapplication.domain.interactor

import android.util.Log
import com.hites.movieapplication.domain.executor.PostExecutionThread
import com.hites.movieapplication.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params> (private val threadExecutor: ThreadExecutor,
                                   private val postExecutionThread: PostExecutionThread
){
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params?): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params?) {
        Log.d("MovieApplication", "onUseCase: here")
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())

        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(disposableObserver: Disposable) {
        compositeDisposable.add(disposableObserver)
    }

    fun dispose(){
        if(!compositeDisposable.isDisposed) compositeDisposable.clear()
    }
}