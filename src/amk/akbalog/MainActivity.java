package amk.akbalog;

import java.util.List;

import amk.classes.ActivityHelper;
import amk.classes.BackLog;
import amk.classes.ListViewFragment;
import amk.database.DbBackLog;
import amk.interfaces.OnUserSelectCategory;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

/*
 * Applications main activity, contains ListViewFragment which displays all the data
 * */

public class MainActivity extends Activity implements OnUserSelectCategory {
	private DbBackLog backLogs;
	private TextView tv;
	private OnUserSelectCategory ousc;
	private MainActivity thisActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//helper for when selected item on the fragmentList
		thisActivity = this;

		//listener when selecting category items
		ousc = (OnUserSelectCategory)this;
		
		setContentView(R.layout.activity_main);

		tv = (TextView)this.findViewById(R.id.textViewBacklogTitle);
		
		//on load, ensure that first star is selected and stepping is 1 and max is number of backlogs..
		backLogs = new DbBackLog(this);
		backLogs.open();
		List<BackLog> backLogsData = backLogs.getAllBacklogs();
		
		if(backLogsData.size() > 0) {
			BackLog b = backLogsData.get(0);
			tv.setText(b.getName());
		}

		//configure RatingBar
		RatingBar rb = (RatingBar)this.findViewById(R.id.ratingBarSelectedBacklog);
		rb.setNumStars(backLogsData.size());
		rb.setMax(backLogsData.size());
		rb.setStepSize((float)1);
		rb.setProgress(1);
		backLogs.close();
		
		rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				backLogs.open();
				BackLog b = backLogs.getBacklogById((int)rating);
				tv.setText(b.getName());
				
				//refresh listview!
				ousc.onItemSelected((int)rating);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuItemAddToDo:
			addToDoDialog();
			return true;
		case R.id.menuItemAbout:
			viewAboutUs();
			return true;
		case R.id.menuItemNews:
			viewNews();
			return true;
		}
		return false;
	}

	@Override
	public void onItemSelected(int category) {
		//helper for when selected item on the categoryList
		ListViewFragment f = (ListViewFragment)thisActivity.getFragmentManager().findFragmentById(R.id.fragment1);
		
		if(f != null && f.isInLayout())
			f.setCategory(category);
	}
	
	private void viewAboutUs() {
		//dialog for about us
		ActivityHelper ah = new ActivityHelper();
		ah.viewAboutUs(this);
    }
	
	private void viewNews() {
		//mode to another activity
		Intent intent = new Intent(this, NewsActivity.class);
		startActivity(intent);
	}
	
	private void addToDoDialog(){
		//adding new todo item to selected category
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dialogAddToDoTitle);

		// Set up the input
		final EditText input = new EditText(this);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	ListViewFragment f = (ListViewFragment)thisActivity.getFragmentManager().findFragmentById(R.id.fragment1);
				
				if(f != null && f.isInLayout())
					f.addToDoToSelectedCategory(input.getText().toString());
		    }
		});
		builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
	}
}


