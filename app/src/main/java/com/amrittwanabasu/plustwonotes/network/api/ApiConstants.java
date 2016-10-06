package com.amrittwanabasu.plustwonotes.network.api;

import com.amrittwanabasu.plustwonotes.BuildConfig;

public final class ApiConstants {

    public static final String API_URL = BuildConfig.PLUSTWONOTES_API_URL;
    public static final String API_ID = BuildConfig.PLUSTWONOTES_API_ID;
    public static final String API_SECRET = BuildConfig.PLUSTWONOTES_API_SECRET;

    public static final String API_AUTH_URL = API_URL + "authenticate";
    public static final String API_V1_URL = API_URL + "v1/";
    public static final String API_SUBJECTS_URL = API_V1_URL + "subjects";
    public static final String API_CATEGORIES_URL = API_V1_URL + "categories";
}
