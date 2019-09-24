package com.warriorminds.comments.network;

public interface RedditClientCallback <T> {

    void onSuccess(T t);

    void onFailure(String error);

}
