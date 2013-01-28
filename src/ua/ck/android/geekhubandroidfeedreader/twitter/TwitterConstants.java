package ua.ck.android.geekhubandroidfeedreader.twitter;


public class TwitterConstants {

    public static final String CONSUMER_KEY = "FTlZLyzUi9CvnJq2mXPGg";
    public static final String CONSUMER_SECRET= "XsHB2AyLGmoaPnRfs3JfrQklbLQivwOdu1gzm5mR0";

    public static final String REQUEST_URL = "https://api.twitter.com/oauth/request_token";
    public static final String ACCESS_URL = "https://api.twitter.com/oauth/access_token";
    public static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";

    public static final String OAUTH_CALLBACK_SCHEME	= "geekhubtwitter";
    public static final String OAUTH_CALLBACK_HOST	= "callback";
    public static final String OAUTH_CALLBACK_URL	=  OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;

}
