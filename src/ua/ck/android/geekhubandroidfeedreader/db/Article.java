package ua.ck.android.geekhubandroidfeedreader.db;

import com.j256.ormlite.field.DatabaseField;

public class Article {

	public final static String ARTICLE_ID_FIELD_NAME = "sid";	
	public final static String ARTICLE_TITLE_FIELD_NAME = "title";	
	public final static String ARTICLE_TEXT_FIELD_NAME = "text";
	public final static String ARTICLE_DATE_FIELD_NAME = "article_date";
	
	public Article() {
		super();
	}
	
	@DatabaseField(canBeNull = false, columnName = ARTICLE_ID_FIELD_NAME, unique = true, id = true)
	private String id;
	
	@DatabaseField(canBeNull = false, columnName = ARTICLE_TITLE_FIELD_NAME, useGetSet= true)
	private String title;
	
	@DatabaseField(canBeNull = false, columnName = ARTICLE_TEXT_FIELD_NAME, useGetSet= true)
	private String text;
	
	@DatabaseField(canBeNull = false, columnName = ARTICLE_DATE_FIELD_NAME, useGetSet= true)
	private String articledate;

	
	public Article(String id, String title, String text, String articledate) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.articledate = articledate;
	}


	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getArticledate() {
		return articledate;
	}
	public void setArticledate(String articledate) {
		this.articledate = articledate;
	}

	public String getId() {
		return id;
	}	
}