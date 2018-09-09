package com.warriorminds.redditsampleapp.di

import com.warriorminds.redditsampleapp.api.RedditRetrofitService
import com.warriorminds.redditsampleapp.managers.RedditManager
import com.warriorminds.redditsampleapp.managers.RedditManagerImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRedditManager(): RedditManager {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return RedditManagerImpl(retrofit.create(RedditRetrofitService::class.java))
    }
}