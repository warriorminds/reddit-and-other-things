package com.warriorminds.comments.data.store.post;

import com.warriorminds.comments.domain.RedditResponse;

import java.util.List;

import rx.Observable;

public interface PostStoreDataSource {

    Observable<List<RedditResponse>> getPost(String permalink);

}