package com.warriorminds.comments.data.store.list;

import com.warriorminds.comments.domain.RedditContent;

import java.util.List;

import rx.Observable;

public interface ListPostsStoreDataSource {

    Observable<List<RedditContent>> getPosts(String subreddit);

    Observable<List<RedditContent>> getPaginatedPosts(String subreddit);

}