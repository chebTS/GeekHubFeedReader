package ua.ck.android.geekhubandroidfeedreader.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.support.ConnectionSource;

public class ArticleDAO extends BaseDaoImpl<Article, Integer> {
	
   protected ArticleDAO(ConnectionSource connectionSource, Class<Article> dataClass) throws SQLException{
       super(connectionSource, dataClass);
   }
   
   //===========Query's:
   public List<Article> getAllArticles() throws SQLException{
       return this.queryForAll();
   }
   
   public void DeleteByMyId(String sid)throws SQLException{
	   DeleteBuilder<Article, Integer> deleteBuilder = deleteBuilder();
	   deleteBuilder.where().eq(Article.ARTICLE_ID_FIELD_NAME, sid);
	   PreparedDelete<Article> preparedDelete = deleteBuilder.prepare();
	   delete(preparedDelete);
   }
   
   /*
   public void deleteComentsByProfile(Profile p)throws SQLException{ 
	   DeleteBuilder<Comment, Integer> deleteBuilder = deleteBuilder();	   
	   deleteBuilder.where().eq(Comment.COMMENTS_PROFILE_FIELD_NAME, p);
	   PreparedDelete<Comment> preparedDelete = deleteBuilder.prepare();
	   delete(preparedDelete);
   }
   */
}
