Nick Nagy, CSE 373, Section AA, Chloe Lathe

1. Topological Sort

A B C D E F G
0 1 1 2 2 1 0
Queue: A G

Queue: G
Output: A

B C D E F
0 1 1 2 1
Queue: G B

Queue: B
Output: A G

C D E F
1 1 2 0
Queue: B F

Queue: F
Output: A G B

C D E
1 0 1
Queue: F D

Queue: D
Output: A G B F

C E
1 1
Queue: D

Queue:
Output: A G B F D

C E
1 0
Queue: E

Queue:
Output: A G B F D E

C
0
Queue: C
Output: A G B F D E C

2. Minimum Spanning Trees

KRUSKAL'S:

Pending:
1: (B,E) (C,G) (D,G)
2: (A,B) (C,D) (E,F)
3: (A,E) (B,F) (E,G)
4: (A,D) (F,G)

Final Order:
(B,E) (C,G) (D,G) (A,B) (E,F) (E,G)
  1	2     3     4     5     6

(C,D), (A,E) and (B,F) are not added because they are already part of the subset when they're reached in the pending list.
(A,D) and (F,G) are not added because output size reaches V-1 first.

PRIM'S:

A: Known; Cost = 0
B: Not known; Cost = 2; Prev = A
D: Not known; Cost = 4; Prev = A
E: Not known; Cost = 3; Prev = A

*visit Vertex B*

B: Known; Cost = 2; Prev = A
D: Not known; Cost = 4; Prev = A
E: Not known; Cost = 1; Prev = B
F: Not known; Cost = 3; Prev = B

*visit Vertex E*

E: Known; Cost = 3; Prev = B
D: Not known; Cost = 4; Prev = A
F: Not known; Cost = 2; Prev = E
G: Not known; Cost = 3; Prev = E

*visit Vertex F*

F: Known; Cost = 2; Prev = E
D: Not known; Cost = 4; Prev = A
G: Not known; Cost = 3; Prev = E

*visit Vertex G*

G: Known; Cost = 3; Prev = E
D: Not known; Cost = 1; Prev = G
C: Not known; Cost = 1; Prev = G

Final Order (after C and D visited)
1. A: Prev = none
2. B: Prev = A
3. E: Prev = B
4. F: Prev = E
5. G: Prev = E
6. C: Prev = G
7. D: Prev = G

3.

reverse(Map<Vertex, Set<Vertex>> graph){
	graph2 = new Map
	for(Vertex v: graph.keySet()){
		for(Vertex x: graph.get(v)){
			graph.get(x).add(v);
		}
	}
	return graph2;
}

nextAdjacentVertices(Map<Vertex, Set<Vertex>> graph, Vertex source){
	nA = new Set
	for(Vertex v: graph.get(source)){
		for(Vertex x: graph.get(v)){
			nA.add(x);
		}
	}
	return nA
}

In reverse(), the worst-case is that every Vertex is connnected to every other Vertex in the first graph (a very dense graph). Since every connection must be checked before the method could end, this worst-case runtime is O(V^2).

In nextAdjacentVertices(), the worst-case again is that each Vertex is connected to every other Vertex. In which case to get the adjacent Vertices would require looking at all Vertices, and to get their adjacent Vertices would require looking at all Vertices again. Assuming that Set.add() is O(1) amortized, this method is also O(V^2).


4.

a) Dijkstra's does not look for shortest path, only shortest cost path. At least with a PriorityQueue implementation it will sometimes find the LONGER of the two shortest cost paths, because more Vertices will tend to mean lower-cost edges between. If we could keep track of known or previously-visited paths - perhaps with a Class similar to the one in this assignment - then we can keep using Dijkstra's algorithm until all known "shortest" paths have been found. Then we can compare the size of the different Paths' set of Vertices and return the one with the smallest size.

b) Dijkstra's is very dependent on all edges having non-negative costs. For example: A is connected to B and C. B is connected to C. If we try to find the shortest cost path from A to C using Dijkstra's, and the edge weight of A to C is less than the weight from A to B, then it doesn't matter if there is a negative cost from B to C that makes A->B->C less expensive. A to C was already found.

c) The example in part 4b) would still fail if a constant k was added to all edges, because A->C would still be less expensive than A->B. In general, this strategy will not work because a path with a negative edge cost is the shortest cost path BECAUSE of the negative cost: if Dijkstra's worked for negative weights, finding the cost of this path would involve subtraction (for example, if we compared a path of cost 1 to a path of cost 2+(-2)). But by adding k to all edges and making them all positive, all path analyses are additive (now in the previous example we're comparing a path of cost of 3 to a path of cost 4+(0)). With this strategy the negative cost path is no longer necessarily the shortest cost path.

5. I physically drew out all connections between the vertex.txt and edges.txt files so I could visualize what input was supposed to return what. To check what was happening at each step of shortestPath() I copied most of my code into a client method, temporarily set MyGraph's fields to public, and used println() statements to check that the Queue and Stack were emptying and inserting as I intended them to. I had a lot of difficulty with abstraction while debugging. As an example, my Comparable method in the Vertex class was working correctly, as I saw that the first index in the Queue was always the new smallest-cost Vertex. However, after multiple calls to shortestPath in FindPaths I was getting NullPointerExceptions(). After printing out each Vertex's label, cost and path at different points during the method, I figured out that this was because I was using direct references to the Vertices in MyGraph's vertexMap, and when the shortestPath() method ended, the Vertices still had their new lowest cost instead of resetting at infinite cost. It was a good practice for remembering to copy elements.

I implemented Dijkstra's with a PriorityQueue(). To maintain constant runtime for finding a Vertex in the Queue, I used a HashSet for unknown Vertices and changed the costs there. Then I added the new lower-cost Vertex to the PriorityQueue without removing any values from the Queue. Even though there are multiple Vertices with the same label in the Queue this way, the higher-cost duplicates will never be reached (they will be ignored by the algorithm once the smallest-cost replicate becomes known). Having duplicates in the Queue means additional wasted space, but the overall runtime for add() is still O(log(N)). Even if there are ten duplicates for every Vertex in the graph and the added Vertex must percolate up the heap, O(log(10N)) is still asymptotically O(log(N)).