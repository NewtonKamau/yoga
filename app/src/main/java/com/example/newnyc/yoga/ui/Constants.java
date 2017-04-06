package com.example.newnyc.yoga.ui;

import com.example.newnyc.yoga.BuildConfig;

/**
 * Created by newnyc on 4/3/17.
 */

public class Constants {
//    adding prefarences to help store the data
    public static final String PREFERENCES_LOCATION_KEY = "location";
//    saving a restaurant to Firebase
    public  static  final String FIREBASE_CHILD_STUDIO = "studio";
//    creating firebase object
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
    public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_TOKEN_SECRET = BuildConfig.YELP_TOKEN_SECRET;
//    put the url to be sewarched by your API
    public static final String YELP_BASE_URL = "https://api.yelp.com/v2/search?term=yoga studio";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
}
