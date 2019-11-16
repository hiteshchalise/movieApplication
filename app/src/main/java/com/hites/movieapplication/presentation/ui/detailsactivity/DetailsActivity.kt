package com.hites.movieapplication.presentation.ui.detailsactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hites.movieapplication.R
import com.hites.movieapplication.core.NetworkHandler
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var networkHandler: NetworkHandler

    lateinit var detailsViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent: Intent = intent
        val id = intent.getIntExtra("id", 0)

        detailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
        detailsViewModel.loadNowPlayingList(!networkHandler.isConnected, id)
        detailsViewModel.movieDetailsLiveData.observe(this, Observer {
            Log.d("MovieApplication", "onCreate: $it")
        })
        detailsViewModel.failureMovieDetailsLiveData.observe(this, Observer {
            Log.d("MovieApplication", "onCreate: $it")
        })
    }
}
