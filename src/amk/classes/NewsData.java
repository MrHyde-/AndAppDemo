package amk.classes;

public class NewsData {
	private String title;
	private String url;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
    public String toString() {
    	String resultString = "";
    			
    	resultString += getTitle();

    	//for helping debug..
    	//resultString += " :: link :: " + getUrl();
   	
    	return resultString;
    }
}
