package com.goopai.app.adapter;

import java.util.List;
import com.goopai.app.bean.News;
import com.goopai.oschina.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewNewsAdapter extends BaseAdapter {

	private Context context;
	private List<News> listItems;
	private LayoutInflater listContainer;
	private int itemViewResource;

	public ListViewNewsAdapter(Context context, List<News> data, int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context);
		this.listItems = data;
		this.itemViewResource = resource;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = listContainer.inflate(itemViewResource, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.news_listitems_title);
			viewHolder.author = (TextView) convertView.findViewById(R.id.news_listitems_author);
			viewHolder.date = (TextView) convertView.findViewById(R.id.news_listitems_date);
			viewHolder.count = (TextView) convertView.findViewById(R.id.news_listitem_commentCount);
			viewHolder.flag = (ImageView) convertView.findViewById(R.id.news_listitems_flag);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		News news = listItems.get(position);
		viewHolder.title.setText(news.getTitle());
		viewHolder.author.setText(news.getAuthor());
		viewHolder.date.setText(news.getPubDate());
		viewHolder.count.setText(news.getRecommentCount() + "");
		viewHolder.flag.setVisibility(View.VISIBLE);
		return convertView;
	}

	private class ViewHolder {

		public TextView title;
		public TextView author;
		public TextView date;
		public TextView count;
		public ImageView flag;
	}
}
