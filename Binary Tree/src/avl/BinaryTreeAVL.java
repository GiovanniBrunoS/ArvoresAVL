package avl;

public class BinaryTreeAVL {

	private Node root;

	public BinaryTreeAVL() {
		super();
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insertNode(DataAVL data) {
		this.root = insertNode(root, data);
	}

	private Node insertNode(Node currentNode, DataAVL data) {
		if (currentNode == null)
			return new Node(data);
		if (currentNode.getData().getId() >= data.getId())
			currentNode.setLeft(insertNode(currentNode.getLeft(), data));
		else
			currentNode.setRight(insertNode(currentNode.getRight(), data));

		return rebalance(currentNode);
	}

	public DataAVL getData(int id) {
		return getData(root, id);
	}

	private DataAVL getData(Node currentNode, int id) {
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
		if (currentNode == null)
			return null;

		if (id == currentNode.getData().getId()) {
			if (currentNode.getLeft() == null && currentNode.getRight() == null)
				return null;
			else if (currentNode.getLeft() == null)
				return currentNode.getRight();
			else if (currentNode.getRight() == null)
				return currentNode.getLeft();
			else {
				DataAVL data = getDataFromRightMost(currentNode.getLeft());
				currentNode.setData(data);
				currentNode.setLeft(removeNode(currentNode.getLeft(), data.getId()));
			}
		} else if (id < currentNode.getData().getId()) {
			currentNode.setLeft(removeNode(currentNode.getLeft(), id));
		} else {
			currentNode.setRight(removeNode(currentNode.getRight(), id));
		}
		return rebalance(currentNode);
	}

	private DataAVL getDataFromRightMost(Node currentNode) {
		if (currentNode.getRight() == null)
			return currentNode.getData();
		return getDataFromRightMost(currentNode.getRight());
	}

	private int getNodeHeight(Node currentNode) {
		if (currentNode == null)
			return -1;
		return currentNode.getHeight();
	}

	private void updateNodeHeight(Node currentNode) {
		currentNode
				.setHeight(1 + Math.max(getNodeHeight(currentNode.getLeft()), getNodeHeight(currentNode.getRight())));
	}

	private int getBalance(Node currentNode) {
		if (currentNode == null)
			return 0;
		return getNodeHeight(currentNode.getRight()) - getNodeHeight(currentNode.getLeft());
	}

	private Node rebalance(Node currentNode) {
		updateNodeHeight(currentNode);
		int balance = getBalance(currentNode);
		if (balance > 1) {
			if (getBalance(currentNode.getRight()) > 0) {
				currentNode = rotateLeft(currentNode);
			} else {
				currentNode.setRight(rotateRight(currentNode.getRight()));
				currentNode = rotateLeft(currentNode);
			}
		} else if (balance < -1) {
			if (getBalance(currentNode.getLeft()) < 0) {
				currentNode = rotateRight(currentNode);
			} else {
				currentNode.setLeft(rotateLeft(currentNode.getLeft()));
				currentNode = rotateRight(currentNode);
			}
		}
		return currentNode;
	}

	private Node rotateLeft(Node y) {
		Node x = y.getRight();
		Node z = x.getLeft();
		y.setRight(z);
		x.setLeft(y);
		updateNodeHeight(y);
		updateNodeHeight(x);
		return x;
	}

	private Node rotateRight(Node y) {
		Node x = y.getLeft();
		Node z = x.getRight();
		y.setLeft(z);
		x.setRight(y);
		updateNodeHeight(y);
		updateNodeHeight(x);
		return x;
	}

	public Boolean balanceCheck() {
		LeafPositions pos = new LeafPositions();

		pos = checkTree(root, pos, 0);
//		System.out.println("Maior:" + pos.getHighest());
//		System.out.println("Menor:" + pos.getLowest());
		if (pos.calcDiff() > 1)
			return false;
		else
			return true;
	}

	private LeafPositions checkTree(Node currentNode, LeafPositions pos, int height) {
		if (currentNode == null)
			return pos;

		if (currentNode.getLeft() == null && currentNode.getRight() == null) {
			if (pos.getHighest() == -1) {
				pos.setHighest(height);
				pos.setLowest(height);
//				System.out.println("Maior: "+currentNode.getData().getId() + " | " + height);
//				System.out.println("Menor: "+currentNode.getData().getId() + " | " + height);
			}
			if (pos.getHighest() < height) {
				pos.setHighest(height);
//				System.out.println("Maior: "+currentNode.getData().getId() + " | " + height);
			}
			if (pos.getLowest() > height) {
				pos.setLowest(height);
//				System.out.println("Menor: "+currentNode.getData().getId() + " | " + height);
			}
		} else {
			checkTree(currentNode.getLeft(), pos, height + 1);
			checkTree(currentNode.getRight(), pos, height + 1);
		}
		return pos;
	}

	public void removeRight() {
		this.root = removeRight(this.root);
	}

	private Node removeRight(Node currentNode) {
		if (currentNode == null)
			return null;

		currentNode.setRight(null);

		return currentNode;
	}
	
	public void rebalanceTree() {
		do {
			this.root = rebalanceTree(this.root);
		}while(rebalanceCheck(this.root, false));
	}
	
	private Boolean rebalanceCheck(Node currentNode, Boolean bool) {
		if (currentNode == null)
			return false;
		if (currentNode.getRight() != null)
			bool = rebalanceCheck(currentNode.getRight(), bool);
		else if(getBalance(currentNode) > 1 || getBalance(currentNode) < -1)
			return true;
		if (currentNode.getLeft() != null)
			bool = rebalanceCheck(currentNode.getLeft(), bool);
		else if(getBalance(currentNode) > 1 || getBalance(currentNode) < -1)
			return true;
		return bool;
	}

	private Node rebalanceTree(Node currentNode) {
		if (currentNode == null)
			return null;
		if (currentNode.getRight() != null)
			currentNode.setRight(rebalanceTree(currentNode.getRight()));
		else if (currentNode.getLeft() != null)
			currentNode.setLeft(rebalanceTree(currentNode.getLeft()));

		return rebalance(currentNode);
	}

	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}

}
