package com.warriorminds.redditsampleapp.di

import com.warriorminds.redditsampleapp.RedditApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    InjectorModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<RedditApp>