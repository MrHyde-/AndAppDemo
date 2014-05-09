package amk.classes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	
	public Boolean isToDoTooOld(ToDo t) {
		Boolean isTooOld = false;
		
		String dtStart = t.getCreatedDate();  
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
		
		try {  
		    Date date = (Date) format.parse(dtStart);  
		    
		    Calendar c = Calendar.getInstance(); 
		    long millsNow = c.get(Calendar.MILLISECOND);
		    long mills = date.getTime() - millsNow;
		    float days = mills/(1000 * 60 * 60 * 24);
		    
		    if(days > 7)
		    {
		    	isTooOld = true;
		    }
		    
		    System.out.println(date);
		} catch (ParseException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();
		    
		} 
		
		return isTooOld;
	}
}
