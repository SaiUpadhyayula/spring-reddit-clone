package com.programming.techie.springredditclone.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiPaths {
    private static final String API = "/api";
    public static final String POST_API_MAPPINGS = API + "/post";
    public static final String SUBREDDIT_API_MAPPING = API + "/subreddit";

    public static final String QUERY_ALL = "/query/all";
    public static final String QUERY_ID = "/query/{id}";
    public static final String CREATE = "/create";

    public static final String VOTE_ID = "/vote/{id}";
    public static final String ID_POSTS_ALL = "{id}/posts/all";
}
