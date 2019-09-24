package com.warriorminds.comments.network;

import com.warriorminds.comments.domain.RedditResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RedditApi {

    @GET("/r/{subreddit}/.json")
    Observable<RedditResponse> getSubreddit(@Path("subreddit") String subreddit, @Query("after") String after);

    @GET("{permalink}.json")
    Observable<List<RedditResponse>> getPost(@Path(value = "permalink",encoded = true) String permalink);

}
