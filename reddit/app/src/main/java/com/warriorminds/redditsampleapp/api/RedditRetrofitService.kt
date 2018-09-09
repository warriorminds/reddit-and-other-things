package com.warriorminds.redditsampleapp.api

import com.warriorminds.redditsampleapp.models.RedditListingResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditRetrofitService {
    @GET("top.json")
    fun getTop(@Query("limit") limit: Int = 10,
               @Query("after") after: String = ""): Single<RedditListingResponse>
}