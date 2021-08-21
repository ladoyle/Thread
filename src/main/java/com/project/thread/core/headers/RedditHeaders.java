package com.project.thread.core.headers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static com.project.thread.core.ApplicationConstants.*;

@Getter
@AllArgsConstructor
public enum RedditHeaders {

    USER_AGENT(USER_AGENT_H, USER_AGENT_V),
    AUTHORIZATION(AUTHORIZATION_H, StringUtils.EMPTY);

    String key;
    String value;
}
