package com.hites.movieapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hites.movieapplication.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.liveDataNowPlayingList.observe(this, Observer {
            Log.d("MovieApplication: ", "OnCreate: ${it.size}")
        })
        mainViewModel.failureNowPlayingLiveData.observe(this, Observer{
            Log.e("MovieApplication: ", "Failed: {$it}")
        } )

        mainViewModel.liveDataPopularMovieList.observe(this, Observer {
            Log.d("MovieApplication: ", "OnCreate: ${it.size}")
        })
        mainViewModel.failureGetPopularLiveData.observe(this, Observer{
            Log.e("MovieApplication: ", "Failed: {$it}")
        } )
        mainViewModel.loadNowPlayingList(false)
        mainViewModel.loadPopularList(false)
    }
}
