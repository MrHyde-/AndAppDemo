package amk.interfaces;

import java.util.List;

import amk.classes.NewsData;

public interface NewsListener {
	public void updateNews(List<NewsData> news);
}
