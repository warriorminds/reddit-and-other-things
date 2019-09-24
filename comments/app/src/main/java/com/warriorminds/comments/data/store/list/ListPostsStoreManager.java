package com.warriorminds.comments.data.store.list;

import com.warriorminds.comments.domain.RedditContent;

import java.util.List;

import rx.Observable;

public class ListPostsStoreManager implements ListPostsStoreDataSource {

    private ListPostsStoreDataSource mRemotePostsStore;
    private boolean isInternetAvailable;

    public ListPostsStoreManager(ListPostsStoreDataSource mRemotePostsStore, boolean isInternetAvailable) {
        this.mRemotePostsStore = mRemotePostsStore;
        this.isInternetAvailable = isInternetAvailable;
    }

    public ListPostsStoreManager(ListPostsStoreDataSource mRemotePostsStore) {
        this.mRemotePostsStore = mRemotePostsStore;
        this.isInternetAvailable = isInternetAvailable;
    }

    @Override
    public Observable<List<RedditContent>> getPosts(String subreddit) {
        return mRemotePostsStore.getPosts(subreddit);
    }

    @Override
    public Observable<List<RedditContent>> getPaginatedPosts(String subreddit) {
        return mRemotePostsStore.getPaginatedPosts(subreddit);
    }

}
