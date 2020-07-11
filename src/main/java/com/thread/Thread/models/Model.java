package com.thread.Thread.models;

public abstract class Model {
    long id;
    String message;

    abstract long getId();
    abstract String getMessage();
}
