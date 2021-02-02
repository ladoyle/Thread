package com.project.thread.core;

public interface ApplicationConstants {

    String DEFAULT_SCHEME = "https";

    interface Reddit {
        String ACCESS_TOKEN_HOST = "www.reddit.com";
        String ACCESS_TOKEN_URL = "/api/v1/access_token";
    }


    // Reddit kv pairs
    String AUTHORIZATION_H = "Authorization";

    String USER_AGENT_H = "User-Agent";
    String USER_AGENT_V = "Thread Mobile App: android:com.project.thread:v1.0.0 (by /u/healfein)";
    String GRANT_TYPE_H = "grant_type";
    String GRANT_TYPE_V = "refresh_token";
    String REFRESH_TOKEN_H = "refresh_token";

}
