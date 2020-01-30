package com.android.androidrecruitchallenge.util;

import java.io.Serializable;

public final class Constants implements Serializable {

    public final static String BASE_URL = "https://api.github.com/";
    public final static String PATH_REPOSITORY_JAVA = "search/repositories";
    public final static String PATH_REPOS_PULLS = "repos/{owner}/{repository}/pulls";
    public final static String LANGUAGE_REQUEST_JAVA = "language:Java";
    public final static String SORT_MODE_STARTS = "stars";
    public final static String REPOSITORY_OBJECT_KEY = "repository";

    public Constants(){
        throw new IllegalStateException("Utility class");
    }
}
