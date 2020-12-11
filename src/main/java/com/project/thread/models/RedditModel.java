package com.project.thread.models;

public class RedditModel extends Model{
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    String getMessage() {
        return this.message;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    String getKeySecret() {
        return this.keySecret;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    String getTokenSecret() {
        return this.tokenSecret;
    }
}
