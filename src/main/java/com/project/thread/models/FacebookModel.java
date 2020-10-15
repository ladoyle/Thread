package com.project.thread.models;

public class FacebookModel extends Model {

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getKey() {
        return this.key;
    };

    @Override
    public String getKeySecret() {
        return this.keySecret;
    };

    @Override
    public String getToken() {
        return this.token;
    };

    @Override
    public String getTokenSecret() {
        return this.tokenSecret;
    };
}
