package binary;

public class BinaryTreeBin {

	private Node root;

	public BinaryTreeBin() {
		super();
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insertNode(DataBin data) {
		this.root = insertNode(root, data);
	}

	private Node insertNode(Node currentNode, DataBin data) {
		if (currentNode == null)
			return new Node(data);
		if (currentNode.getData().getId() >= data.getId())
			currentNode.setLeft(insertNode(currentNode.getLeft(), data));
		else
			currentNode.setRight(insertNode(currentNode.getRight(), data));

		return rebalance(currentNode);
	}

	public DataBin getData(int id) {
		return getData(root, id);
	}

	private DataBin getData(Node currentNode, int id) {
		if (currentNode == null)
			return null;
		else if (currentNode.getData().getId() == id)
			return currentNode.getData();
		else if (currentNode.getData().getId() > id)
			return getData(currentNode.getLeft(), id);
		else
			return getData(currentNode.getRight(), id);
	}
	
	public void removeNode(int id) {
		this.root = removeNode(this.root, id);
	}
	
	private Node removeNode(Node currentNode, int id) {
		if(currentNode == null)
			return null;
		
		if(id == currentNode.getData().getId()) {
			if(currentNode.getLeft() == null && currentNode.getRight() == null)
				return null;
			else if(currentNode.getLeft() == null)
				return currentNode.getRight();
			else if(currentNode.getRight() == null)
				return currentNode.getLeft();
			else {
				DataBin data = getDataFromRightMost(currentNode.getLeft());
				currentNode.setData(data);
				currentNode.setLeft(removeNode(currentNode.getLeft(), data.getId()));
			}
		}else if (id < currentNode.getData().getId()) {
			currentNode.setLeft(removeNode(currentNode.getLeft(), id));
		}else {
			currentNode.setRight(removeNode(currentNode.getRight(), id));
		}
		return rebalance(currentNode);
	}
	
	private DataBin getDataFromRightMost(Node currentNode) {
		if(currentNode.getRight() == null)
			return currentNode.getData();
		return getDataFromRightMost(currentNode.getRight());
	}

	
	private int getNodeHeight(Node currentNode) {
		if (currentNode == null)
			return -1;
		return currentNode.getHeight();
	}

	private void updateNodeHeight(Node currentNode) {
		currentNode.setHeight(1+Math.max(getNodeHeight(currentNode.getLeft()), getNodeHeight(currentNode.getRight())));
	}
	
	private Node rebalance(Node currentNode) {
		updateNodeHeight(currentNode);
		return currentNode;
	}
	

	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}
	
}
