package com.hites.movieapplication.domain.util

data class LoadingState(val state: State, val errorCode: ErrorCode?, val message: String?){
    companion object{
        fun loading(): LoadingState{
            return LoadingState(State.LOADING, null, null)
        }

        fun success(errorCode: ErrorCode?, message: String?): LoadingState{
            return LoadingState(State.SUCCESS, errorCode, message)
        }

        fun error(errorCode: ErrorCode, message: String?): LoadingState{
            return LoadingState(State.ERROR, errorCode, message)
        }
    }
}