package amk.classes;

import amk.akbalog.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

/*
 * BroadcastReceiver which is making notifications if needed 
 * 
 */

public class TooOldToDoReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context c, Intent i) {
		//Building notification
		Notification.Builder nb = new Notification.Builder(c);
		nb.setContentTitle(c.getText(R.string.notifyTitle));
		
		Bundle extras = i.getExtras();
	    if(extras != null)
	    {
	    	String data = extras.getString("title");
	    	Resources res = c.getResources();
			String dialogMessage = String.format(res.getString(R.string.notifyMessage), data);
			nb.setContentText(dialogMessage);
	    }
				
		nb.setSmallIcon(R.drawable.ic_launcher);
		NotificationManager nm = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(0, nb.build());
	}
}
