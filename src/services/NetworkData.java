package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NetworkData extends Service {

	private ServBinder seb = new ServBinder(this);

	@Override
	public IBinder onBind(Intent intent) {
		return seb;
	}
	
	public String getNews() {
		//try to fetch the data
		return getJSONData();
	}
	
	private String getJSONData() {
		InputStream is = null;
		int len = 1024;
		
		URL url;
		try {
			url = new URL("http://users.metropolia.fi/~anttita/dev/yle.php");
		
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        
        int response = conn.getResponseCode();        
        Log.d("NetworkData", "The response is: " + response);
        
        is = conn.getInputStream();

        // Convert the InputStream into a string
        String contentAsString = readIt(is, len);
        return contentAsString;
        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Network ERROR!";
		}
	}
	
	private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}

}
