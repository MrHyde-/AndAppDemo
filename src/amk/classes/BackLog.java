package amk.classes;

/*
 * Simple class to implement the categories for ToDo items 
 * 
 */
public class BackLog {
	private int id;
	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
