package com.warriorminds.redditsampleapp.di

import com.warriorminds.redditsampleapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}