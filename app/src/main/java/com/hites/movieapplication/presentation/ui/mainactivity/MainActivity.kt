package com.hites.movieapplication.presentation.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.hites.movieapplication.R
import com.hites.movieapplication.core.NetworkHandler
import com.hites.movieapplication.domain.model.Movie
import com.hites.movieapplication.presentation.ui.detailsactivity.DetailsActivity
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var networkHandler: NetworkHandler

    private lateinit var mainViewModel: MainViewModel
    lateinit var bannerImage: ImageView

    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var nowPlayingRecyclerView: RecyclerView

    private lateinit var popularViewAdapter: PopularViewAdapter
    private lateinit var nowPlayingViewAdapter: NowPlayingViewAdapter

    private lateinit var popularViewManager: RecyclerView.LayoutManager
    private lateinit var nowPlayingViewManager: RecyclerView.LayoutManager

    private var popularMovieList: List<Movie> = ArrayList()
    private var nowPlayingMovieList: List<Movie> = ArrayList()
    private var bannerList: List<BannerModel> = ArrayList()

    private lateinit var timer: Timer
    var imageIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularRecyclerView = findViewById(R.id.recyclerViewPopular)
        nowPlayingRecyclerView = findViewById(R.id.recyclerViewNowPlaying)
        bannerImage = findViewById(R.id.bannerImage)

        popularViewManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        nowPlayingViewManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        popularViewAdapter =
            PopularViewAdapter(this) {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("id", it)
                startActivity(intent)
            }
        nowPlayingViewAdapter =
            NowPlayingViewAdapter(this) {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("id", it)
                startActivity(intent)
            }

        popularRecyclerView.layoutManager = popularViewManager
        popularRecyclerView.adapter = popularViewAdapter

        nowPlayingRecyclerView.layoutManager = nowPlayingViewManager
        nowPlayingRecyclerView.adapter = nowPlayingViewAdapter
        initializeViewModel()
        loadPage()
        changeBanner()
    }

    private fun initializeViewModel() {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.liveDataNowPlayingList.observe(this, Observer { movieList ->
            Log.d("MovieApplication: ", "OnCreate: ${movieList.size}")
            nowPlayingMovieList = movieList
            bannerList = movieList.map {
                BannerModel(
                    it.id,
                    it.poster_path
                )
            }
            nowPlayingViewAdapter.submitList(nowPlayingMovieList)
        })
        mainViewModel.failureNowPlayingLiveData.observe(this, Observer {
            Log.e("MovieApplication: ", "Failed: {$it}")
        })

        mainViewModel.liveDataPopularMovieList.observe(this, Observer {
            Log.d("MovieApplication: ", "OnCreate: ${it.size}")
            popularMovieList = it
            popularViewAdapter.submitList(popularMovieList)
        })
        mainViewModel.failureGetPopularLiveData.observe(this, Observer {
            Log.e("MovieApplication: ", "Failed: {$it}")
        })
    }

    private fun changeBanner() {
        timer = Timer("banner")
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if(bannerList.isEmpty()) return
                imageIndex = (imageIndex + 1) % bannerList.size
                bannerImage.load("https://image.tmdb.org/t/p/w500/" + bannerList[imageIndex].posterPath){
                    transformations(CircleCropTransformation())
                }
            }
        }, 1000, 10000)
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun loadPage() {
        val networkAvailability: Boolean = networkHandler.isConnected
        Log.d("MovieApplication", "MainActivity: $networkAvailability")

        // first load from cache then refresh
        mainViewModel.loadNowPlayingList(true)
        mainViewModel.loadPopularList(true)

        if(networkAvailability){
            mainViewModel.loadNowPlayingList(false)
            mainViewModel.loadPopularList(false)
        }

    }
}
