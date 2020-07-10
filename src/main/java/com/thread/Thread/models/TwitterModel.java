package com.thread.Thread.models;

public class TwitterModel implements Model{
    private long id;
    private String message;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getMessage() { return message; }

}
