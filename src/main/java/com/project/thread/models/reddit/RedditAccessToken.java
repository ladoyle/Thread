package com.project.thread.models.reddit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedditAccessToken {
    @NonNull private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String scope;
}
