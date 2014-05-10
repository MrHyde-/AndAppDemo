package amk.services;

import amk.classes.HttpAsyncTask;
import amk.interfaces.NewsListener;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*
 * Service which is binded and calls the web page 
 * 
 */

public class ServNetworkData extends Service {

	private ServBinder seb = new ServBinder(this);

	@Override
	public IBinder onBind(Intent intent) {
		return seb;
	}

	public void loadData(NewsListener l) {
		// call AsynTask to perform network operation on separate thread
		HttpAsyncTask tasker = new HttpAsyncTask();
		tasker.setListener(l);
		tasker.execute("http://users.metropolia.fi/~anttita/dev/yle.php");
	}
}
