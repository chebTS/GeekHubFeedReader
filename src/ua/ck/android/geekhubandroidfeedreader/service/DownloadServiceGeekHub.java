package ua.ck.android.geekhubandroidfeedreader.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import ua.ck.android.geekhubandroidfeedreader.MainActivity;
import ua.ck.android.geekhubandroidfeedreader.R;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class DownloadServiceGeekHub extends Service {
	private int TIME_TO_UPDATE = 3600; //once an hour
	private final int RES_UPD = 1001; // MUST be the same as in MainActivity
	private final String TAG_JSON = "TAG_JSON"; // MUST be the same as in MainActivity
	private final static String PARAM_PINTENT = "pendingIntent";  // MUST be the same as in MainActivity
	private SharedPreferences preferences;
	private final String url = "http://android-developers.blogspot.com/feeds/posts/default?alt=json";
	private Context context;
	private NotificationManager nm;
	PendingIntent pi;
	final String LOG_TAG = "myLogs";
	private Thread updateThread;

	  public void onCreate() {
	    super.onCreate();
	    context = getApplicationContext();
	    nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    updateThread = new Thread(new Runnable() {
		      public void run() {
		    	  while(true){
		    		  Log.d(LOG_TAG, " Service alive");
		    		  try {
			            TimeUnit.SECONDS.sleep(TIME_TO_UPDATE);
			          } catch (InterruptedException e) {
			            e.printStackTrace();
			          }
		    		  if (isModefied(url)){
		    			  downloadJSON();		    			  
		    			  sendNotify();
		    		  }
		    	  }	 
		      }
		    });
	    Log.d(LOG_TAG, "onCreate");
	  }
	  
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    Log.d(LOG_TAG, "onStartCommand");
	    if (intent != null){
		    pi = intent.getParcelableExtra(PARAM_PINTENT);
		    Runnable r =  new Runnable() {
				@Override
				public void run() {
					if (isModefied(url)){
		    			  try {
		    				  Log.d(LOG_TAG, "JSON downloaded");
		    				  pi.send(getApplicationContext(), RES_UPD, new Intent().putExtra(TAG_JSON, downloadJSON()));
		    			  } catch (CanceledException e) {
								e.printStackTrace();
		    			  }
		    		}else{
		    			try {
							pi.send(getApplicationContext(), RES_UPD, new Intent().putExtra(TAG_JSON, getOldJSON()));
						} catch (CanceledException e) {
							e.printStackTrace();
						}
		    		}
					if (!updateThread.isAlive() ){
						updateThread.start();
					}
				}
			};
			new Thread(r).start();	
	    }else{
	    	if (!updateThread.isAlive() ){
				updateThread.start();
			}
	    }	
	    return super.onStartCommand(intent, flags, startId);
	  }
	  

	  public void onDestroy() {
	    super.onDestroy();
	    Log.d(LOG_TAG, "onDestroy");
	  }

	  public IBinder onBind(Intent intent) {
	    Log.d(LOG_TAG, "onBind");
	    return null;
	  }

	  private boolean isModefied(String uri){
			Header[] headers;
			preferences = context.getSharedPreferences("ETAG", 0);
			String newETAG, oldETAG;
			oldETAG = preferences.getString("ETAG", "def-=+Value");
			DefaultHttpClient mHttpClient = new DefaultHttpClient();
			HttpGet dhttpget = new HttpGet(uri);
			HttpResponse dresponse = null;
			try{
				dresponse = mHttpClient.execute(dhttpget);
				headers = dresponse.getAllHeaders();
				for (int i = 0; i < headers.length; i++){
					if (headers[i].getName().equals("ETag")){
						newETAG = headers[i].getValue();
						if (!newETAG.equals(oldETAG)){
							preferences.edit().putString("ETAG", newETAG).commit();
							Log.i("ETAGS",oldETAG + " ||| "+ newETAG);
							return true;
						}
						break;
					}
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	  
  public String downloadJSON() {
		StringBuilder sb = new StringBuilder();
		DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpGet dhttpget = new HttpGet(url);
		HttpResponse dresponse = null;
		try{
			dresponse = mHttpClient.execute(dhttpget);
		}catch (IOException e) {
			e.printStackTrace();
		}
		int status = dresponse.getStatusLine().getStatusCode();
		if (status == 200){
			char[] buffer = new char[1];
			try{
				InputStream content = dresponse.getEntity().getContent();
	            InputStreamReader isr = new InputStreamReader(content);
	            while (isr.read(buffer) != -1) {
	                sb.append(buffer);
	            }
			}catch (IOException e) {
				e.printStackTrace();
			}
			//saving JSON 
			preferences = context.getSharedPreferences(TAG_JSON, 0); 
			preferences.edit().putString(TAG_JSON, sb.toString()).commit();
		}else{	
			Log.i("Error","Connection error : "+ Integer.toString(status));
		}
		return sb.toString();
	}
  
  private String getOldJSON(){
	  preferences = context.getSharedPreferences(TAG_JSON, 0); 
	  String s = preferences.getString(TAG_JSON, "");
	  return s;
  }
 
  
  void sendNotify(){
		Notification notif = new Notification(R.drawable.ic_launcher, "Android feed updates", System.currentTimeMillis());
	    Intent intent = new Intent(DownloadServiceGeekHub.this, MainActivity.class);
	    intent.putExtra("Update", "Update");
	    PendingIntent pIntent = PendingIntent.getActivity(DownloadServiceGeekHub.this, 0, intent, 0);
	    notif.setLatestEventInfo(DownloadServiceGeekHub.this, "There are updates", "Android developers updates", pIntent);
	    notif.flags |= Notification.FLAG_AUTO_CANCEL;
	    nm.notify(1, notif);
	}
}
