package com.warriorminds.comments.data.repositories;

import android.support.annotation.NonNull;

import com.warriorminds.comments.data.PostsDataSource;
import com.warriorminds.comments.domain.RedditContent;

import rx.Observable;

public class PostsRepository implements PostsDataSource {

    private PostsDataSource mPostsRemoteDataSource;
    private PostsDataSource mPostsLocalDataSource;
    private boolean internetAccess;

    private static PostsRepository ourInstance;

    public static PostsRepository getInstance(PostsDataSource tasksRemoteDataSource, PostsDataSource mPostsLocalDataSource, boolean internetAccess) {
        if (ourInstance == null) {
            ourInstance = new PostsRepository(tasksRemoteDataSource, mPostsLocalDataSource, internetAccess);
        }
        return ourInstance;
    }

    private PostsRepository(@NonNull PostsDataSource postsRemoteDataSource, @NonNull PostsDataSource postsLocalDataSource, boolean internetAccess) {
        mPostsRemoteDataSource = postsRemoteDataSource;
        mPostsLocalDataSource = postsLocalDataSource;
        this.internetAccess = internetAccess;
    }

    @Override
    public Observable<RedditContent> getPaginatedPosts() {
        if (internetAccess) {
            return mPostsRemoteDataSource.getPaginatedPosts();
        } else {
            return mPostsLocalDataSource.getPaginatedPosts();
        }
    }

    @Override
    public Observable<RedditContent> getPosts() {
        if (internetAccess) {
            return mPostsRemoteDataSource.getPosts();
        } else {
            return mPostsLocalDataSource.getPosts();
        }
    }

    @Override
    public void setFavorite(RedditContent redditContent, boolean favorite) {
        mPostsLocalDataSource.setFavorite(redditContent, favorite);
    }

}
