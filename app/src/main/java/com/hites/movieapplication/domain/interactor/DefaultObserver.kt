package com.hites.movieapplication.domain.interactor

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T>: DisposableObserver<T>(){

    override fun onNext(t: T) {
        // no operation by default
    }

    override fun onError(e: Throwable) {
        // no operation by default
    }

    override fun onComplete() {
        // no operation by default
    }

}