package amk.classes;

import java.util.List;

import amk.akbalog.R;
import amk.database.DbToDo;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListViewFragment extends ListFragment {
	
	DbToDo todos;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                                    Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		Activity currentActivity = getActivity();
		
		//set items from the first category..
		todos = new DbToDo(currentActivity);
		todos.open();

		List<ToDo> list = todos.getToDosByBackLog(1);
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<ToDo> adapter = new ArrayAdapter<ToDo>(currentActivity, android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		
		return v;
	}
	
	public void setCategory(int selectedCategory) {
		//refresh list view..!..
		List<ToDo> list = todos.getToDosByBackLog(selectedCategory);
		ArrayAdapter<ToDo> adapter = new ArrayAdapter<ToDo>(getActivity(), android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
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
}
