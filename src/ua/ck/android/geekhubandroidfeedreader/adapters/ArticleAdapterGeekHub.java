package ua.ck.android.geekhubandroidfeedreader.adapters;


import ua.ck.android.geekhubandroidfeedreader.R;
import ua.ck.android.geekhubandroidfeedreader.db.Article;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArticleAdapterGeekHub extends BaseAdapter {
	private Article[] articles;
	private LayoutInflater inflater;
	
	public ArticleAdapterGeekHub(Article[] articles, LayoutInflater inflater) {
		super();
		this.articles = articles;
		this.inflater = inflater;
	}
	
	public void ChangeData(Article[] articles){
		this.articles = articles;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return articles.length;
	}

	@Override
	public Article getItem(int position) {
		return articles[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
		((TextView)convertView.findViewById(R.id.txtTitle)).setText(getItem(position).getTitle());
		((TextView)convertView.findViewById(R.id.txtDate)).setText(getItem(position).getArticledate());
		return convertView;
	}
}