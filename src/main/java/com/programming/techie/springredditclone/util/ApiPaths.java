package com.programming.techie.springredditclone.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiPaths {
    private static final String API = "/api";
    public static final String POST_API_MAPPINGS = API + "/posts";
    public static final String SUBREDDIT_API_MAPPING = API + "/subreddit";
    public static final String COMMENT_API_MAPPING = API + "/comments";

    public static final String QUERY_ALL = "/query/all";
    public static final String QUERY_ID = "/query/{id}";
    public static final String QUERY_POST_ID = "/query/{postId}";
    public static final String CREATE = "/create";

    public static final String ID_POSTS_ALL = "{id}/posts/all";
    public static final String QUERY_BY_SUBREDDIT = "/query/all/subreddit/{subredditId}";

    public static final String QUERY_BY_USERNAME = "/query/user/{name}";

}
