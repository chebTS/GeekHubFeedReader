package ua.ck.android.geekhubandroidfeedreader.twitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;
import oauth.signpost.OAuth;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class TwitterUtils {

    public static boolean isAuthenticated(SharedPreferences prefs) {

        String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
        String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");

        AccessToken a = new AccessToken(token,secret);
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(TwitterConstants.CONSUMER_KEY, TwitterConstants.CONSUMER_SECRET);
        twitter.setOAuthAccessToken(a);

        try {
            twitter.getAccountSettings();
            return true;
        } catch (TwitterException e) {
            return false;
        }
    }

    public static void sendTweet(SharedPreferences prefs,String msg) throws TwitterException{
        String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
        String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");

        AccessToken a = new AccessToken(token,secret);
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(TwitterConstants.CONSUMER_KEY, TwitterConstants.CONSUMER_SECRET);
        twitter.setOAuthAccessToken(a);
        twitter.updateStatus(msg);
    }

    public static void saveAccessToken(SharedPreferences prefs, AccessToken a) {
        final SharedPreferences.Editor edit = prefs.edit();
        edit.putString(OAuth.OAUTH_TOKEN, a.getToken());
        edit.putString(OAuth.OAUTH_TOKEN_SECRET, a.getTokenSecret());
        edit.commit();
    }

    public static void showToast(Context context, Boolean result)
    {
        final Activity activity = (Activity)context;
        final Boolean res = result;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(res)
                {
                    Toast.makeText(activity, "Tweet send!", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(activity, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static void sendTweetExec(Context context, String msg)
    {
        if(TwitterUtils.isAuthenticated(PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext())))
        {
            try
            {
                TwitterUtils.sendTweet(PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()), msg);
                showToast(context, true);
            } catch (TwitterException e)
            {
                showToast(context, false);
            }
        } else
        {
            Intent i = new Intent(context.getApplicationContext(), TwitterConnector.class);
            i.putExtra("tweet_msg",msg);
            context.startActivity(i);
        }
    }
}