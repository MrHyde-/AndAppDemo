package amk.classes;

import amk.akbalog.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/*
 * Class to have common activity methods
 * */
public class ActivityHelper {
	
	public ActivityHelper()
	{
		
	}
	
	
	//displays about us Dialog from multiple activity
	public void viewAboutUs(Activity onActivity) {
		AlertDialog.Builder adb = new AlertDialog.Builder(onActivity);
		adb.setTitle(R.string.settingAbout);
		adb.setMessage(R.string.messageAbout);
		
		adb.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog ad = adb.create();
		ad.show();
    }
	
	//is used to determinate if there are too old ToDo items at the db
	public Boolean isToDoTooOld(ToDo t) {
		Boolean isTooOld = false;
		
		long oneWeekInMills = (7*24*60*60*1000);
		
		long tooOldtime = System.currentTimeMillis() - oneWeekInMills;
		long todoTime = Long.valueOf(t.getCreatedDate());
		
		if(todoTime < tooOldtime)
		{
			isTooOld = true;
		}
		
		return isTooOld;
	}
}
