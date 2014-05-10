package amk.akbalog;

import java.util.List;

import amk.classes.ActivityHelper;
import amk.classes.NewsData;
import amk.interfaces.NewsListener;
import amk.services.ServBinder;
import amk.services.ServNetworkData;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Applications second activity, contains ListView which displays news data from JSON
 * */

public class NewsActivity extends ListActivity implements NewsListener {
	private ServiceConnection sc;
	private NewsActivity copySelf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//helper to be used when service is connected
		copySelf = this;
		
		setContentView(R.layout.activity_news);
		
		//bind to service
		sc = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName n, IBinder b) {
				((ServBinder)b).passListener(copySelf);
			}
			@Override
			public void onServiceDisconnected(ComponentName name) {

			}
		};

		//bind the network service to collecting json data
		Intent i = new Intent(this, ServNetworkData.class);
		this.bindService(i, sc, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		this.unbindService(sc);
		super.onDestroy();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menuItemAboutNews) {
			viewAboutUs();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void viewAboutUs() {
		ActivityHelper ah = new ActivityHelper();
		ah.viewAboutUs(this);
    }

	@Override
	public void updateNews(List<NewsData> news) {
		// TODO Auto-generated method stub
		ArrayAdapter<NewsData> adapter = new ArrayAdapter<NewsData>(this, android.R.layout.simple_list_item_1, news);
		setListAdapter(adapter);
	}

	@Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
		//get item from the list so we can catch the name
		NewsData newsForWebView = (NewsData)l.getItemAtPosition(position);

		//show webview with url content
		Intent intent = new Intent(this, NewsViewActivity.class);
		intent.putExtra("title", newsForWebView.getTitle());
		intent.putExtra("link", newsForWebView.getUrl());
		startActivity(intent);
	  }
}
