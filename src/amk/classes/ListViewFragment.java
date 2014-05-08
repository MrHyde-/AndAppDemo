package amk.classes;

import amk.akbalog.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListViewFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			                                    Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		return v;
	}
	
	public void setCategory(int selectedCategory) {
		//refresh list view..!..
		//String numberToString = String.valueOf(selectedCategory);  
		
		//used this to show me that the response is really coming to here! heureka
		//remember to import toast library..
		//Toast.makeText(getActivity(), numberToString, Toast.LENGTH_SHORT).show();
	}
}
