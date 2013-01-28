package ua.ck.android.geekhubandroidfeedreader.fragments;

import java.sql.SQLException;

import ua.ck.android.geekhubandroidfeedreader.R;
import ua.ck.android.geekhubandroidfeedreader.db.Article;
import ua.ck.android.geekhubandroidfeedreader.db.ArticleDAO;
import ua.ck.android.geekhubandroidfeedreader.db.HelperFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.actionbarsherlock.app.SherlockFragment;

public class Fragment2 extends SherlockFragment {
	WebView webView;
	Article curArticle;
	
	public void setData(Article article){
		curArticle = article;
		try{
			ArticleDAO articleDAO = HelperFactory.GetHelper().getArticleDAO();
			//((MainActivity)getSherlockActivity()).setIsCurrentArticleLiked(true);
		}catch (SQLException e) {
			// TODO: handle exception
		}
		if (webView != null){
			webView.loadData(curArticle.getText(), "text/html", "UTF-8");
		}
	}
	
	
	
	public Article getCurArticle() {
		return curArticle;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_2, null);
		webView = (WebView)v.findViewById(R.id.webView);
		if (curArticle != null){
			webView.loadData(curArticle.getText(), "text/html", "UTF-8");
		}else{
			webView.loadData("Choose article", "text/html", "UTF-8");
		}
		return v;
	}
}
