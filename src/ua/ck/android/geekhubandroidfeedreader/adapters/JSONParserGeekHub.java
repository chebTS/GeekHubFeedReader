package ua.ck.android.geekhubandroidfeedreader.adapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ua.ck.android.geekhubandroidfeedreader.db.Article;


public class JSONParserGeekHub {

	
	public static Article[] parse(String input){
		Article[] articles = {};
		JSONObject jArticle, jTitle, jContent, jId, jDate, jResponse = null;
		try{
			jResponse = new JSONObject(input);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		JSONArray jEntry = jResponse.optJSONObject("feed").optJSONArray("entry");
		if (jEntry != null){
			articles = new Article[jEntry.length()];
			for (int i = 0; i < jEntry.length(); i++){
				jArticle = jEntry.optJSONObject(i);
				jId = jArticle.optJSONObject("id");
				jTitle = jArticle.optJSONObject("title");
				jContent = jArticle.optJSONObject("content");
				jDate = jArticle.optJSONObject("updated");
				articles[i] = new Article(jId.optString("$t"),jTitle.optString("$t"), jContent.optString("$t"), jDate.optString("$t").substring(0, 10));
			}
		}
		return articles;
	}
}