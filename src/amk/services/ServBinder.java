package amk.services;

import android.os.Binder;

public class ServBinder extends Binder {
	
	private ServNetworkData d;

	public ServBinder(ServNetworkData d) {
		this.d = d;
	}
	
	public ServNetworkData getServiceNetworkData() {
		return d;
	}
}
