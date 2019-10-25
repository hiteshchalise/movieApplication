package com.hites.movieapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hites.movieapplication.R
import com.hites.movieapplication.presentation.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var nowPlayingViewModel: NowPlayingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nowPlayingViewModel = ViewModelProviders.of(this, viewModelFactory).get(NowPlayingViewModel::class.java)
        nowPlayingViewModel.getNowPlayingListLiveData().observe(this, Observer {
            Log.d("MovieApplication: ", "OnCreate: ${it.size}")
        })
        nowPlayingViewModel.loadMoreMovies()
    }
}
