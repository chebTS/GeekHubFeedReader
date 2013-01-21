package ua.ck.android.geekhubandroidfeedreader;

import android.app.Application;
import android.util.Log;

public class FeedReaderApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Application","onCreate");
	}
	
}
