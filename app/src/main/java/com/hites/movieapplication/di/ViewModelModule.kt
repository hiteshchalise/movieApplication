package com.hites.movieapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hites.movieapplication.presentation.ui.MainViewModel
import com.hites.movieapplication.presentation.viewmodel.ViewModelFactory
import com.hites.movieapplication.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun nowPlayingViewModel(mainViewModel: MainViewModel): ViewModel
}