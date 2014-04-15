package amk.akbalog;

import java.util.List;

import amk.classes.BackLog;
import amk.database.DbBackLog;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private DbBackLog backLogs;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView)this.findViewById(R.id.textViewBacklogTitle);
		
		//on load, ensure that first star is selected and stepping is 1 and max is number of backlogs..
		backLogs = new DbBackLog(this);
		backLogs.open();
		List<BackLog> backLogsData = backLogs.getAllBacklogs();
		
		if(backLogsData.size() > 0){
			BackLog b = backLogsData.get(0);
			tv.setText(b.getName());
		}

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
			}
		});
	}

}
