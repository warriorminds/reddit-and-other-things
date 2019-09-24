package com.warriorminds.comments.data;

import com.warriorminds.comments.domain.RedditContent;

import rx.Observable;

public interface PostsDataSource {

    Observable<RedditContent> getPosts();

    Observable<RedditContent> getPaginatedPosts();

    void setFavorite(RedditContent redditContent, boolean favorite);

}
