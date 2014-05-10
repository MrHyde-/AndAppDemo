package amk.interfaces;

import java.util.List;

import amk.classes.NewsData;

/*
 * This is used to notify activity to refresh its listview when
 * data is collected and also parsed
 * 
 */

public interface NewsListener {
	public void updateNews(List<NewsData> news);
}
