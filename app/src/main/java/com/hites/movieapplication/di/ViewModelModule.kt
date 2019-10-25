package com.hites.movieapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hites.movieapplication.presentation.ui.NowPlayingViewModel
import com.hites.movieapplication.presentation.viewmodel.ViewModelFactory
import com.hites.movieapplication.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    protected abstract fun nowPlayingViewModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel
}