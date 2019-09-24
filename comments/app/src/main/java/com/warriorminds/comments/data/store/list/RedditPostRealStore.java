package com.warriorminds.comments.data.store.list;

import com.nytimes.android.external.store.base.Fetcher;
import com.nytimes.android.external.store.base.impl.RealStore;
import com.warriorminds.comments.data.store.RedditPostsRequest;
import com.warriorminds.comments.domain.RedditContent;

import java.util.List;

import rx.Observable;

public class RedditPostRealStore extends RealStore<List<RedditContent>, RedditPostsRequest> {

    public RedditPostRealStore(Fetcher<List<RedditContent>, RedditPostsRequest> fetcher) {
        super(fetcher);
    }

    Observable<List<RedditContent>> getPosts(RedditPostsRequest request) {
        return get(request);
    }

}

