// Nick Nagy, CSE 373, Section AA, Chloe Lathe

// This class can be used to store and retrieve
// data in order of highest to lowest priority

import java.util.List;

public class ThreeHeap implements PriorityQueue {
	private static final int INIT_SIZE = 10;

	private double[] heapArray;
	private int numElements;

	// constructs an empty PriorityQueue
	public ThreeHeap() {
		makeEmpty();
	}

	@Override
	// returns true if the PriorityQueue is empty, otherwise returns false
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	// returns the number of elements in the PriorityQueue
	public int size() {
		return numElements;
	}

	@Override
	// pre: PriorityQueue is not empty, otherwise throws new EmptyHeapException
	// returns the element at the top of the PriorityQueue (the highest-priority
	// element)
	public double findMin() {
		if (isEmpty()) {
			throw new EmptyHeapException();
		}
		return heapArray[1];
	}

	@Override
	// adds the element double x to the bottom of the PriorityQueue, then swaps it
	// with other elements until the PriorityQueue is in proper order (highest to
	// lowest priority)
	// if the PriorityQueue runs out of capacity, it will double in size
	public void insert(double x) {
		numElements++;
		if (numElements == heapArray.length) {
			heapArray = grow(heapArray);
		}
		heapArray[numElements] = x;
		percolateUp(numElements, heapArray);
	}

	@Override
	// pre: PriorityQueue is not empty, otherwise throws new EmptyHeapException
	// removes and returns the element at the top of the PriorityQueue (the
	// highest-priority element)
	// if only a fourth or less of the PriorityQueue's total capacity is used, 
	// PriorityQueue resets to half its size to save space
	// sets the next highest-priority element at the top of the PriorityQueue
	public double deleteMin() {
		double min = findMin();
		heapArray[1] = heapArray[numElements];
		heapArray[numElements] = 0;
		numElements--;
		if (numElements == heapArray.length / 4 && heapArray.length > INIT_SIZE) {
			heapArray = shrink(heapArray);
		}
		percolateDown(1, heapArray, numElements);
		return min;
	}

	@Override
	// empties the current PriorityQueue and creates a new PriorityQueue from
	// the elements in the list
	public void buildQueue(List<Double> list) {
		makeEmpty();
		// removes all elements from the list and adds them to the PriorityQueue
		// if space in the PrioritQueue runs out, the PriorityQueue doubles in size
		while (!list.isEmpty()) {
			numElements++;
			if (numElements == heapArray.length) {
				heapArray = grow(heapArray);
			}
			heapArray[numElements] = list.remove(0);
		}
		// organizes the elements added to the PriorityQueue in order of highest to
		// lowest priority
		for (int i = (numElements + 1) / 3; i > 0; i--) {
			percolateDown(i, heapArray, numElements);
		}
	}

	@Override
	// resets the PriorityQueue to an "empty" PriorityQueue (does not contain
	// any elements)
	public void makeEmpty() {
		heapArray = new double[INIT_SIZE];
		numElements = 0;
	}
	
	// returns the PriorityQueue in the form of a String
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("[");
		if(!isEmpty()){
			s.append(findMin());
			for(int i = 2; i <= numElements; i++){
				s.append(", " + heapArray[i]);
			}
		}
		s.append("]");
		return s.toString();
	}

	// doubles the size of the double[] heap with all the elements
	// still in their correct indexes then returns the updated heap
	private double[] grow(double[] heap) {
		double[] replacementArray = new double[heap.length * 2];
		for (int i = 1; i < heap.length; i++) {
			replacementArray[i] = heap[i];
		}
		return replacementArray;
	}

	// halves the size of the double[] heap with all the elements
	// still in their correct indexes then returns the updated heap
	private double[] shrink(double[] heap) {
		double[] replacementArray = new double[heap.length / 2];
		for (int i = 1; i < replacementArray.length; i++) {
			replacementArray[i] = heap[i];
		}
		return replacementArray;
	}

	// continuously swaps any two elements in the double[] heap if the element
	// at int index is of a higher priority than its parent index
	// updates index to the index of the parent it was swapped with
	// all elements guaranteed to be in correct order if the index is the first
	// element in the heap or if its parent is of higher priority
	// returns the index when elements are all in correct order
	private double percolateUp(int index, double[] heap) {
		while (index > 1 && heap[index] < heap[(index + 1) / 3]) {
			int parent = (index + 1) / 3;
			double parentData = heap[parent];
			heap[parent] = heap[index];
			heap[index] = parentData;
			index = parent;
		}
		return index;
	}

	// continuously swaps any two elements in the double[] heap if the element
	// at int index is of a lower priority than one of its child elements
	// updates index to the index of the child it was swapped with
	// all elements guaranteed to be in correct order if the index is greater
	// than int (elementNum+1)/3
	// returns the index when elements are all in correct order
	private double percolateDown(int index, double[] heap, int elementNum) {
		// elements greater than elementNum+1/3 guaranteed to be in correct
		// order
		while (index <= (elementNum + 1) / 3) {
			// finds the target index to swap with
			int target = getTargetIndex(index, heap, elementNum);
			double targetData = heap[target];
			if (targetData > heap[index])
				break;
			heap[target] = heap[index];
			heap[index] = targetData;
			index = target;
		}
		return index;
	}

	// looks at the elements at the "children" indexes of the element in
	// the double[] heap at int index
	// the children exist if their indexes are less than int elementNum
	// returns the index of whichever child has the smallest element
	private int getTargetIndex(int index, double[] heap, int elementNum) {
		int mid = index * 3;
		int left = mid - 1;
		int right = mid + 1;
		// if a right child exists, it will return whichever
		// child's element is smallest
		if (elementNum >= right) {
			if (heap[right] > heap[left] && heap[mid] > heap[left]) {
				return left;
			} else if (heap[right] > heap[mid] && heap[right] > heap[mid]) {
				return mid;
			} else {
				return right;
			}
			// if the left and mid childs exist, it will return
			// child's element is smaller
		} else if (elementNum >= mid) {
			if (heap[mid] > heap[left]) {
				return left;
			} else {
				return mid;
			}
			// if only the left child exists, returns that child's
			// element
		} else {
			return left;
		}
	}
}
