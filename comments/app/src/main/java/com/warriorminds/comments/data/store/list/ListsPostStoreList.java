package com.warriorminds.comments.data.store.list;

import android.support.annotation.NonNull;

import com.nytimes.android.external.store.base.Fetcher;
import com.nytimes.android.external.store.base.impl.RealStore;
import com.warriorminds.comments.data.store.RedditPostsRequest;
import com.warriorminds.comments.domain.RedditContent;
import com.warriorminds.comments.domain.RedditResponse;
import com.warriorminds.comments.network.RedditApi;

import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.schedulers.Schedulers;

public class ListsPostStoreList implements ListPostsStoreDataSource {

    private RedditApi redditApi;
    private String after;
    private Random random = new Random();
    private RealStore<List<RedditContent>, RedditPostsRequest> remoteStore;

    private Fetcher<List<RedditContent>, RedditPostsRequest> fetcher = new Fetcher<List<RedditContent>, RedditPostsRequest>() {
        @NonNull
        @Override
        public Observable<List<RedditContent>> fetch(@NonNull RedditPostsRequest request) {
            return getPosts(request.getKey(), request.getAfter());
        }
    };

    private Observable<List<RedditContent>> getPosts(String subreddit, String after) {
        return redditApi.getSubreddit(subreddit, after)
                .map(RedditResponse::getData)
                .subscribeOn(Schedulers.io())
                .flatMap(redditData -> {
                    ListsPostStoreList.this.after = redditData.getAfter();
                    return Observable.from(redditData.getChildren());
                })
                .filter(redditPostMetadata -> !redditPostMetadata.getRedditContentData().isNsfw())
                .map(redditPostMetadata -> {
                    int r = random.nextInt(10);
                    if (r % 5 == 0) {
                        redditPostMetadata.setBigPost(true);
                        return redditPostMetadata;
                    } else {
                        return redditPostMetadata;
                    }
                })
                .toList();
    }


    public ListsPostStoreList(RedditApi redditApi) {
        this.redditApi = redditApi;
        this.remoteStore = new RedditPostRealStore(fetcher);
    }

    @Override
    public Observable<List<RedditContent>> getPosts(String subreddit) {
        RedditPostsRequest redditPostsRequest = new RedditPostsRequest(subreddit, RedditContent.class.getSimpleName(), null);
        return remoteStore.get(redditPostsRequest);
    }

    @Override
    public Observable<List<RedditContent>> getPaginatedPosts(String subreddit) {
        RedditPostsRequest redditPostsRequest = new RedditPostsRequest(subreddit, RedditContent.class.getSimpleName(), after);
        return remoteStore.get(redditPostsRequest);
    }

}
