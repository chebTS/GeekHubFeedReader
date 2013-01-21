package ua.ck.android.geekhubandroidfeedreader.db;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.content.Context;

public class HelperFactory {
	   private static DatabaseHelper databaseHelper;

	   public static DatabaseHelper GetHelper(){
	       return databaseHelper;
	   }
	   public static void SetHelper(Context context){
	       databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
	   }
	   public static void ReleaseHelper(){
	       OpenHelperManager.releaseHelper();
	       databaseHelper = null;
	   }	   
}