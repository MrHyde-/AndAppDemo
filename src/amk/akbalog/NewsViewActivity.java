package amk.akbalog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
/*
 * Applications third activity, contains webView which views the actual news
 * */
public class NewsViewActivity extends Activity {

	TextView tvTitle;
	WebView myView;

	//this have to be, 90% of modern web pages is not showing if there is no JS support..
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_view);

		tvTitle = (TextView)this.findViewById(R.id.textViewNewsViewTitle);
		myView = (WebView)this.findViewById(R.id.webViewSportNews);
		
		Bundle extras = getIntent().getExtras();
	    if(extras != null)
	    {
	    	//get data from the intent
	    	String data = extras.getString("title");
	    	tvTitle.setText(data);
	    	String url = extras.getString("link");

	    	myView.getSettings().setJavaScriptEnabled(true);
	    	myView.setWebViewClient(new WebViewClient());
			myView.loadUrl(url);
	    }
	}
}