package amk.akbalog;

import amk.classes.ActivityHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NewsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		//start news collecting..
		
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

}
