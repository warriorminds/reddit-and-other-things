package com.warriorminds.comments.data.remote;

import com.warriorminds.comments.data.PostsDataSource;
import com.warriorminds.comments.domain.RedditContent;
import com.warriorminds.comments.domain.RedditResponse;
import com.warriorminds.comments.network.RedditApi;

import java.util.Random;

import rx.Observable;
import rx.schedulers.Schedulers;

public class RemotePostsRepository implements PostsDataSource {

    private RedditApi redditApi;
    private String after;
    private Random random = new Random();

    private static RemotePostsRepository ourInstance;

    public static RemotePostsRepository getInstance(RedditApi redditApi) {
        if (ourInstance == null) {
            ourInstance = new RemotePostsRepository(redditApi);
        }
        return ourInstance;
    }

    private RemotePostsRepository(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    @Override
    public Observable<RedditContent> getPosts() {
        return getPosts(null);
    }

    @Override
    public Observable<RedditContent> getPaginatedPosts() {
        return getPosts(after);
    }

    @Override
    public void setFavorite(RedditContent redditContent, boolean favorite) {
        // Nothing to do as this is only local.
        redditContent.setFavorite(favorite);
    }

    private Observable<RedditContent> getPosts(String after) {
        return redditApi.getSubreddit("popular", after)
                .map(RedditResponse::getData)
                .subscribeOn(Schedulers.io())
                .flatMap(redditData -> {
                    RemotePostsRepository.this.after = redditData.getAfter();
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
                });
    }

}
