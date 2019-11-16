package com.hites.movieapplication.di

import com.hites.movieapplication.presentation.ui.detailsactivity.DetailsActivity
import com.hites.movieapplication.presentation.ui.mainactivity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailsActivity(): DetailsActivity
}