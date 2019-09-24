package com.warriorminds.comments.domain;

import java.util.List;

public class Preview {

    private List<RedditImage> images;
    private boolean enabled;

    public List<RedditImage> getImages() {
        return images;
    }

    public boolean isEnabled() {
        return enabled;
    }

}