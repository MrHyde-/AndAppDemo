package amk.classes;



public class ToDo {
	private int Id;
	private String Name;
	private boolean Done;
	private String CreatedDate;
	private int BackLog;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public boolean isDone() {
		return Done;
	}
	public void setDone(boolean status) {
		Done = status;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public int getBackLog() {
		return BackLog;
	}
	public void setBackLog(int backLog) {
		BackLog = backLog;
	}
	
	@Override
    public String toString() {
    	String resultString = "";
    			
    	resultString += getName();

    	//for helping debug..
    	resultString += " :: time :: " + getCreatedDate();
    	
    	return resultString;
    }
}
