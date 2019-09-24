package com.warriorminds.comments.data.store.post;

import android.support.annotation.NonNull;

import com.nytimes.android.external.store.base.Fetcher;
import com.nytimes.android.external.store.base.impl.BarCode;
import com.nytimes.android.external.store.base.impl.Store;
import com.nytimes.android.external.store.base.impl.StoreBuilder;
import com.warriorminds.comments.domain.RedditResponse;
import com.warriorminds.comments.network.RedditApi;

import java.util.List;

import rx.Observable;

public class PostStore implements PostStoreDataSource {

    private RedditApi redditApi;

    private Store<List<RedditResponse>, BarCode> store;

    public PostStore(RedditApi redditApi) {
        this.redditApi = redditApi;
        this.store = StoreBuilder.<List<RedditResponse>>barcode()
                .fetcher(fetcher)
                .open();
    }

    private Fetcher<List<RedditResponse>, BarCode> fetcher = new Fetcher<List<RedditResponse>, BarCode>() {
        @NonNull
        @Override
        public Observable<List<RedditResponse>> fetch(@NonNull BarCode barCode) {
            return getRemotePost(barCode.getKey());
        }
    };

    private Observable<List<RedditResponse>> getRemotePost(String key) {
        return redditApi.getPost(key);
    }

    @Override
    public Observable<List<RedditResponse>> getPost(String permalink) {
        BarCode barCode = new BarCode("Post", permalink);
        return store.get(barCode);
    }

}
