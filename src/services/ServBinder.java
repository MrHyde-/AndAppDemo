package services;

import android.os.Binder;

public class ServBinder extends Binder {
	
	private NetworkData d;

	public ServBinder(NetworkData d) {
		this.d = d;
	}
	
	public NetworkData getNetworkData() {
		return d;
	}

}
