package binary;

public class Node {

	private DataBin data;
	private int height;
	private Node left;
	private Node right;

	public Node(DataBin data) {
		super();
		this.data = data;
		this.height = 0;
		this.left = null;
		this.right = null;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public DataBin getData() {
		return data;
	}

	public void setData(DataBin data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", left=" + left + ", right=" + right + "]" ;
	}

	
	
}
