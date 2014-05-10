package amk.services;

import amk.interfaces.NewsListener;
import android.os.Binder;

/*
 * NetWork service binder 
 * 
 */

public class ServBinder extends Binder {
	
	private ServNetworkData d;

	public ServBinder(ServNetworkData d) {
		this.d = d;
	}
	
	public ServNetworkData getServiceNetworkData() {
		return d;
	}
	
	public void passListener(NewsListener l) {
		d.loadData(l);
	}
}
