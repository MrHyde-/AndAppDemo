package amk.services;

import amk.classes.HttpAsyncTask;
import amk.interfaces.NewsListener;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServNetworkData extends Service {

	private ServBinder seb = new ServBinder(this);

	@Override
	public IBinder onBind(Intent intent) {
		// call AsynTask to perform network operation on separate thread
		return seb;
	}

	public void loadData(NewsListener l) {
		HttpAsyncTask tasker = new HttpAsyncTask();
		tasker.setListener(l);
		tasker.execute("http://users.metropolia.fi/~anttita/dev/yle.php");
	}
}
