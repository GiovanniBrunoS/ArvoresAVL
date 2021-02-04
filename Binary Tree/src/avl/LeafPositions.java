package avl;

public class LeafPositions {

	private int lowest;
	private int highest;
	
	public LeafPositions() {
		this.lowest = -1;
		this.highest = -1;
	}

	public int getLowest() {
		return lowest;
	}

	public void setLowest(int lowest) {
		this.lowest = lowest;
	}

	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}

	public int calcDiff() {
		return Math.abs(highest - lowest);
	}
}
