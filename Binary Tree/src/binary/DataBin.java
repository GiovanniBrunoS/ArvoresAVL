package binary;


public class DataBin {

	private int id;

	public DataBin(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + "]";
	}

	
	
}
