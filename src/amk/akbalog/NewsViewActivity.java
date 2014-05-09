package amk.akbalog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class NewsViewActivity extends Activity {

	TextView tvTitle;
	WebView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_view);

		tvTitle = (TextView)this.findViewById(R.id.textViewNewsViewTitle);
		myView = (WebView)this.findViewById(R.id.webViewSportNews);
		
		Bundle extras = getIntent().getExtras();
	    if(extras != null)
	    {
	    	String data = extras.getString("title");
	    	tvTitle.setText(data);
	    	String url = extras.getString("link");
	    	//this alert is ok, 90% of modern web pages is not showing if there is no JS support..
	    	myView.getSettings().setJavaScriptEnabled(true);
	    	myView.setWebViewClient(new WebViewClient());
			myView.loadUrl(url);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}