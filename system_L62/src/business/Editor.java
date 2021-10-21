package business;

public class Editor {

	private String id;
	private String CV;
	
	public Editor(String id) {
		this.id = id;
		this.CV = "";
	}

	public String getName() {
		return id;
	}
	
	public String getCV() {
		return CV;
	}
}
