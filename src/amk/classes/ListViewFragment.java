package amk.classes;

import java.util.List;

import amk.akbalog.R;
import amk.database.DbToDo;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewFragment extends ListFragment {
	DbToDo todos;
	ToDo toDoForDialog;
	int currentCategory = 1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                                    Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		Activity currentActivity = getActivity();
		
		//set items from the first category..
		todos = new DbToDo(currentActivity);
		todos.open();

		updateArrayAdapter();
		
		return v;
	}
	
	public void setCategory(int selectedCategory) {
		currentCategory = selectedCategory;
		//refresh list view..!..
		updateArrayAdapter();
	}
	
	public void addToDoToSelectedCategory(String toDoText) {
		todos.addToDoToCategory(toDoText, currentCategory);
		Toast.makeText(getActivity(), R.string.toDoAdded, Toast.LENGTH_SHORT).show();
		updateArrayAdapter();
	}
	
	@Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
		//get item from the list so we can catch the name
		toDoForDialog = (ToDo)l.getItemAtPosition(position);
		// change item status
		//dialog 'is this really done?'
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		Resources res = getResources();
		String dialogMessage = String.format(res.getString(R.string.dialogMessage), toDoForDialog.getName());
		
		builder.setMessage(dialogMessage).setTitle(R.string.dialogTitle);

		builder.setPositiveButton(R.string.commonYes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//mark item as done
				toDoForDialog.setDone(true);
				//update to the db
				todos.updateToDo(toDoForDialog);
				//notify user
				Toast.makeText(getActivity(), R.string.toDoMarkedDone, Toast.LENGTH_SHORT).show();
				
				updateArrayAdapter();
			}
		});
		builder.setNegativeButton(R.string.commonNo, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//just close dialog
				dialog.dismiss();
			}
		});
		
		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		dialog.show();
	  }
	
    @Override
    public void onResume() {
    	// open local db
    	todos.open();
    	super.onResume();
    }
    
    @Override
    public void onPause() {
    	// close local db
    	todos.close();
    	super.onPause();
    }
    
    private void updateArrayAdapter() {
		List<ToDo> list = todos.getToDosByBackLog(currentCategory);
		ArrayAdapter<ToDo> adapter = new ArrayAdapter<ToDo>(getActivity(), android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		
		checkOldest();
	}
    
    private void checkOldest() {
    	Activity fragmentsActivity = getActivity();
    	Boolean isTooOld = true;
    	
    	ToDo t = todos.getOldestToDo();
    	
    	if(t != null)
    	{
    		isTooOld = new ActivityHelper().isToDoTooOld(t);
    		
        	if(isTooOld) {
        		Intent i = new Intent(fragmentsActivity, TooOldToDoReceiver.class);
        		i.putExtra("title", t.getName());
        		
        		PendingIntent pi = PendingIntent.getBroadcast(fragmentsActivity, 0, i, 0);
        		AlarmManager am = (AlarmManager) fragmentsActivity.getSystemService(Context.ALARM_SERVICE);
        		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2000, pi);
        	}
    	}
    }
}
