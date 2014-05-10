package amk.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import amk.interfaces.NewsListener;
import android.os.AsyncTask;
import android.util.Log;

/*
 * Class which is fetching JSON data from the network and also handles it 
 * 
 */

public class HttpAsyncTask extends AsyncTask<String, Void, String> {
	NewsListener listener = null;

	public void setListener(NewsListener l) {
		listener = l;
	}


	@Override
	protected String doInBackground(String... urls) {

		return GET(urls[0]);
	}
	
	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		//Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

		try {
			JSONObject json = new JSONObject(result);

			int newsCount = json.getJSONArray("news").length();

			List<NewsData> news = new ArrayList<NewsData>();

			//make list from these!
			for(int i=0; i<newsCount;i++)
			{
				JSONArray articles = json.getJSONArray("news");
				
				NewsData data = new NewsData();
				data.setTitle(articles.getJSONObject(i).getString("title"));
				data.setUrl(articles.getJSONObject(i).getString("link"));
				
				news.add(data);
			}

			if (listener!=null) {
				if (news != null) {
					listener.updateNews(news);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String GET(String url){
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}
}