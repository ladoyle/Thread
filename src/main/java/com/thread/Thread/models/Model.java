package com.thread.Thread.models;

public abstract class Model {

    long id;
    String message;
    String key;
    String keySecret;
    String token;
    String tokenSecret;

    abstract long getId();
    abstract String getMessage();
    abstract String getKey();
    abstract String getKeySecret();
    abstract String getToken();
    abstract String getTokenSecret();
}