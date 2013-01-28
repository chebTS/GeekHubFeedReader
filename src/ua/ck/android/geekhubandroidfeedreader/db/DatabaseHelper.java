package ua.ck.android.geekhubandroidfeedreader.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


	  private static final String TAG = DatabaseHelper.class.getSimpleName();

	  private static final String DATABASE_NAME ="geekhub1.db";
	   
	   private static final int DATABASE_VERSION = 1;
	   
	   
	   private ArticleDAO articleDao = null;
	   
	   public DatabaseHelper(Context context){
	       super(context,DATABASE_NAME, null, DATABASE_VERSION);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
	       try
	       {
	           TableUtils.createTable(connectionSource, Article.class);
	       }
	       catch (SQLException e){
	           Log.e(TAG, "error creating DB " + DATABASE_NAME);
	           throw new RuntimeException(e);
	       }
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
	           int newVer){
	       try{
	           TableUtils.dropTable(connectionSource, Article.class, true);
	           onCreate(db, connectionSource);
	       }
	       catch (SQLException e){
	           Log.e(TAG,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);
	           throw new RuntimeException(e);
	       }
	   }
	   
	  public ArticleDAO getArticleDAO() throws SQLException{
	       if(articleDao == null){
	    	   articleDao = new ArticleDAO(getConnectionSource(), Article.class);
	       }
	       return articleDao;
	   }
	   
	   @Override
	   public void close(){
	       super.close();
	       articleDao = null;
	   }
}