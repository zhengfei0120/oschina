package com.goopai.oschina.ui;

import java.util.ArrayList;
import java.util.List;
import com.goopai.app.adapter.ListViewNewsAdapter;
import com.goopai.app.bean.News;
import com.goopai.app.common.UIHelper;
import com.goopai.app.manager.AppContext;
import com.goopai.app.uitil.NetworkHelper;
import com.goopai.app.widget.PullToRefreshListView;
import com.goopai.oschina.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private ListViewNewsAdapter listViewNewsAdapter;
	private AppContext appContext;
	private List<News> lvNewsData = new ArrayList<News>();

	private PullToRefreshListView lvNews;

	private View lvNews_footer;
	private View lvNews_foot_more;
	private View lvNews_foot_progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appContext = AppContext.getInstance();
		if (!NetworkHelper.isNetworkConnected(getApplicationContext())) {
			UIHelper.toastMessage(this, R.string.network_not_connected);
		}
		initFrameListView();
	}

	/**
	 * init news list
	 */
	private void initNewsListView() {
		listViewNewsAdapter = new ListViewNewsAdapter(getApplicationContext(), lvNewsData, R.layout.news_listitem);
		lvNews_footer = getLayoutInflater().inflate(R.layout.listview_footer, null);
		lvNews_foot_more = (TextView) lvNews_footer.findViewById(R.id.listview_foot_more);
		lvNews_foot_progress = (ProgressBar) lvNews_footer.findViewById(R.id.listview_foot_progress);
		lvNews = (PullToRefreshListView) findViewById(R.id.frame_listview_news);
		// lvNews.addFooterView(lvNews_footer);
		lvNews.setAdapter(listViewNewsAdapter);
	}

	private void initFrameListView() {
		this.initNewsListView();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			UIHelper.exit(this);
		}
		return super.onKeyDown(keyCode, event);
	}
}
