package amk.akbalog;

import services.NetworkData;
import services.ServBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private NetworkData nd;
	private ServiceConnection sc;
	//private boolean bound = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sc = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName n, IBinder b) {
				ServBinder seb = (ServBinder) b;
				nd = seb.getNetworkData();
				//bound = true;
			}
			@Override
			public void onServiceDisconnected(ComponentName name) {
				//bound = false;
			}		
		};
		Intent i = new Intent(this, NetworkData.class);
		this.bindService(i, sc, Context.BIND_AUTO_CREATE);
	}
	
	public void justView(View v) {
		String t = nd.getNews();
		TextView tv = (TextView) this.findViewById(R.id.textView1);
		tv.setText(t);
	}

}
