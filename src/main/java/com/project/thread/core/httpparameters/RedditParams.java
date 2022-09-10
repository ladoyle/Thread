package com.project.thread.core.httpparameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static com.project.thread.core.ApplicationConstants.*;

@Getter
@AllArgsConstructor
public enum RedditParams {

    GRANT_TYPE(GRANT_TYPE_H, GRANT_TYPE_V),
    REFRESH_TOKEN(REFRESH_TOKEN_H, StringUtils.EMPTY);

    private final String key;
    private final String value;
}
