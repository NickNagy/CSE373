/**
 * Representation of a graph vertex
 */
public class Vertex implements Comparable<Vertex> {
	private final String label; // label attached to this vertex
	private int cost;
	private Vertex path;

	/**
	 * Construct a new vertex
	 * 
	 * @param label
	 *            the label attached to this vertex
	 */
	public Vertex(String label) {
		if (label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		path = null;
		cost = Integer.MAX_VALUE;
	}

	/**
	 * Get a vertex label
	 * 
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * A string representation of this object
	 * 
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	// auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	// auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			return other.label == null;
		} else {
			return label.equals(other.label);
		}
	}

	// sets the cost of the Vertex to the parameter
	public void setCost(int cost) {
		this.cost = cost;
	}

	// returns the cost of the Vertex
	public int getCost() {
		return cost;
	}

	// sets the path of the Vertex to the parameter
	public void setPath(Vertex path) {
		this.path = path;
	}

	// returns the path of the Vertex
	public Vertex getPath() {
		return path;
	}

	// returns the difference in cost between
	// this Vertex and v
	@Override
	public int compareTo(Vertex v) {
		return cost - v.getCost();
	}
}