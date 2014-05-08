package amk.akbalog;

import java.util.List;

import amk.classes.BackLog;
import amk.classes.ListViewFragment;
import amk.database.DbBackLog;
import amk.interfaces.OnUserSelectCategory;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnUserSelectCategory {
	private DbBackLog backLogs;
	private TextView tv;
	private OnUserSelectCategory ousc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				backLogs.open();
				BackLog b = backLogs.getBacklogById((int)rating);
				tv.setText(b.getName());
				
				//refresh listview!
				ousc.onItemSelected((int)rating);
			}
		});
	}

	@Override
	public void onItemSelected(int category) {
		// TODO Auto-generated method stub
		ListViewFragment f = (ListViewFragment) this.getFragmentManager().findFragmentById(R.id.fragment1);
		
		if(f != null && f.isInLayout())
			f.setCategory(category);
	}
}
