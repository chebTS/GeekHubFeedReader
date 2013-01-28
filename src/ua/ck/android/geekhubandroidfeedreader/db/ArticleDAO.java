package ua.ck.android.geekhubandroidfeedreader.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

public class ArticleDAO extends BaseDaoImpl<Article, Integer> {
	
   protected ArticleDAO(ConnectionSource connectionSource, Class<Article> dataClass) throws SQLException{
       super(connectionSource, dataClass);
   }
   
   //===========Query's:
   public List<Article> getAllArticles() throws SQLException{
       return this.queryForAll();
   }
   
   public void DeleteByMyId(String sid){
	   
   }
   
   
   
}
