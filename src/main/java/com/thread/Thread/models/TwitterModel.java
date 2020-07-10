package com.thread.Thread.models;

public class TwitterModel implements Model {
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}