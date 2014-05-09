package amk.classes;

import amk.akbalog.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ActivityHelper {
	
	public ActivityHelper()
	{
		
	}
	
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
}
