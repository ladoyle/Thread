package com.project.thread.models.reddit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedditAccessToken implements Serializable {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String scope;
}
