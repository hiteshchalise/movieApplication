package com.hites.movieapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hites.movieapplication.R
import com.hites.movieapplication.core.NetworkHandler
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var networkHandler: NetworkHandler

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViewModel()
        loadPage()
    }

    private fun initializeViewModel(){
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
    }

    private fun loadPage(){
        val networkAvailability: Boolean = networkHandler.isConnected
        Log.d("MovieApplication", "MainActivity: $networkAvailability")
        mainViewModel.loadNowPlayingList(!networkAvailability)
        mainViewModel.loadPopularList(!networkAvailability)
    }
}
