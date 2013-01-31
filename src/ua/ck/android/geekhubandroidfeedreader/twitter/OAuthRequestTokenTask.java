package ua.ck.android.geekhubandroidfeedreader.twitter;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;


public class OAuthRequestTokenTask extends AsyncTask<Void, Void, Boolean> {

    final String TAG = getClass().getName();
    private Context	context;
    Activity activity;
    private OAuthProvider provider;
    private OAuthConsumer consumer;
    String url;

    public OAuthRequestTokenTask(Activity activity, Context context,OAuthConsumer consumer,OAuthProvider provider) {
        this.context = context;
        this.consumer = consumer;
        this.provider = provider;
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            Log.i(TAG, "Retrieving request token from Google servers");
            url = provider.retrieveRequestToken(consumer, TwitterConstants.OAUTH_CALLBACK_URL);
            Log.i(TAG, "Popping a browser with the authorize URL : " + url);
            return true;
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_FROM_BACKGROUND);
            //context.startActivity(intent);            
        } catch (Exception e) {
            Log.e(TAG, "Error during OAUth retrieve request token", e);
            return false;
        }
        
        //return null;
    }

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result){
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_FROM_BACKGROUND);
			//context.startActivity(intent);
			activity.startActivityForResult(intent, 1);
		}
	}
    
    
}