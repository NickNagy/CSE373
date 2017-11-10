// Nick Nagy, CSE 373, Section AA, Chloe Lathe

import java.util.*;

// this class represents a graph with non-negative weighted edges

public class MyGraph implements Graph {

	private final Set<Edge> edges;
	private final Map<Vertex, HashSet<Edge>> vertexMap;

	// constructs a graph with the given vertices v and edges e
	// throws new IllegalArgumentException() if either parameter is null
	// throws new IllegalArgumentException() if any edges in e have negative
	// weights
	// throws new IllegalArgumentException() if two edges in e have the same
	// source and destination but have different weights
	// throws new IllegalArgumentException() if an edge in e points to an vertex
	// that does not exist in v
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		if (v == null || e == null) {
			throw new IllegalArgumentException();
		}
		edges = new HashSet<Edge>();
		for (Edge eg : e) {
			if (eg == null || eg.getWeight() < 0) {
				throw new IllegalArgumentException();
			}
			int weight = eg.getWeight();
			Edge egCopy = new Edge(eg.getSource(), eg.getDestination(), weight);
			edges.add(egCopy);
		}
		vertexMap = buildMap(v, edges);
	}

	// returns the collection of vertices in the graph
	public Collection<Vertex> vertices() {
		return vertexMap.keySet();
	}

	// returns the collection of edges in the graph
	public Collection<Edge> edges() {
		return edges;
	}

	// returns the collection of vertices in the graph that are adjacent to
	// Vertex v
	// throws new IllegalArgumentException if v is null or does not exist in the
	// graph
	public Collection<Vertex> adjacentVertices(Vertex v) {
		if (!vertexExists(v, vertexMap.keySet())) {
			throw new IllegalArgumentException();
		}
		Collection<Vertex> adjacents = new HashSet<Vertex>();
		Set<Edge> edgeSet = vertexMap.get(v);
		for (Edge eg : edgeSet) {
			Vertex adjacent = eg.getDestination();
			adjacents.add(adjacent);
		}
		return Collections.unmodifiableCollection(adjacents);
	}

	// returns the weight of the edge pointing from a to b
	// throws new IllegalArgumentException() if a or b are null, or if a and b
	// do not exist in the graph
	// returns -1 if a and b exist but there is no edge connecting them
	public int edgeCost(Vertex a, Vertex b) {
		if (!verticesExist(a, b, vertexMap.keySet())) {
			throw new IllegalArgumentException();
		}
		Set<Edge> edgeSet = vertexMap.get(a);
		for (Edge eg : edgeSet) {
			if (eg.getDestination().equals(b)) {
				int weight = eg.getWeight();
				return weight;
			}
		}
		return -1;
	}

	// returns true if the vertex v is not null and exists in the Set vertices,
	// otherwise returns false
	private boolean vertexExists(Vertex v, Set<Vertex> vertices) {
		return (v != null && vertices.contains(v));
	}

	// returns true if both a and b are not null and exist in the Set vertices,
	// otherwise returns false
	private boolean verticesExist(Vertex a, Vertex b, Set<Vertex> vertices) {
		return (vertexExists(a, vertices) && vertexExists(b, vertices));
	}
	
	// builds and returns a new map with the given Collections
	// throws new IllegalArgumentException() if two edges in e have the same
	// source and destination but have different weights
	// throws new IllegalArgumentException() if an edge in e points to an vertex
	// that does not exist in v
	private Map<Vertex, HashSet<Edge>> buildMap(Collection<Vertex> v, Collection<Edge> e){
		Map<Vertex, HashSet<Edge>> makeMap = new HashMap<Vertex, HashSet<Edge>>();
		// adds the vertices to the graph
		for(Vertex vx : v){
			if (vx == null) {
				throw new IllegalArgumentException();
			}
			String label = vx.getLabel();
			Vertex vxCopy = new Vertex(label);
			makeMap.put(vxCopy, new HashSet<Edge>());
		}
		// adds the edges to graph based on their source vertex
		for(Edge eg: e){
			if (!verticesExist(eg.getSource(), eg.getDestination(),
					makeMap.keySet())) {
				throw new IllegalArgumentException();
			}
			HashSet<Edge> edgeSet = makeMap.get(eg.getSource());
			for (Edge other : edgeSet) {
				if (other.getDestination().equals(eg.getDestination())
						&& other.getWeight() != eg.getWeight()) {
					throw new IllegalArgumentException();
				}
			}
			edgeSet.add(eg);
		}
		return makeMap;
	}
}