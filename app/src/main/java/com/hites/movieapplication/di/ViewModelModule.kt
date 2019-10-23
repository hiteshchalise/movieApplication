package com.hites.movieapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hites.movieapplication.presentation.NowPlayingViewModel
import com.hites.movieapplication.presentation.ViewModelFactory
import com.hites.movieapplication.presentation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    protected abstract fun nowPlayingViewModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel
}