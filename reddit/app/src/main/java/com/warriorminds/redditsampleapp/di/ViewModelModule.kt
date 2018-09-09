package com.warriorminds.redditsampleapp.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.warriorminds.redditsampleapp.viewmodels.MainActivityViewModel
import com.warriorminds.redditsampleapp.viewmodels.ViewModelFactory
import com.warriorminds.redditsampleapp.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun providesMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun providesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}