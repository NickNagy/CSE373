// Nick Nagy, CSE 373, Section AA, Chloe Lathe

package sorting;

import java.util.Comparator;

// This class can be used to sort elements using merge sort,
// selection sort and insertion sort algorithms

public class GenericSorter {

	// sorts the array of elements in ascending order according to the
	// comparator using the merge sort algorithm
	public static <E> void mergeSort(E[] array, Comparator<E> comparator) {
		E[] tempArray = (E[]) new Object[array.length];
		mergeSort(array, tempArray, comparator, 0, array.length - 1);
	}

	// sorts the array of elements in ascending order according to the
	// comparator using the selection sort algorithm
	public static <E> void selectionSort(E[] array, Comparator<E> comparator) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			int k = i + 1;
			// locates the next smallest value in the array
			while (k < array.length) {
				if (comparator.compare(array[k], array[minIndex]) < 0) {
					minIndex = k;
				}
				k++;
			}
			// swaps the value at i with the next smallest value in the array
			E min = array[minIndex];
			array[minIndex] = array[i];
			array[i] = min;
		}
	}

	// sorts the array of elements in ascending order according to the
	// comparator using the insertion sort algorithm
	public static <E> void insertionSort(E[] array, Comparator<E> comparator) {
		for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
			E currentPacket = array[outerIndex];
			int innerIndex = outerIndex - 1;
			while (innerIndex >= 0
					&& comparator.compare(currentPacket, array[innerIndex]) < 0) {
				array[innerIndex + 1] = array[innerIndex];
				innerIndex--;
			}
			array[innerIndex + 1] = currentPacket;
		}
	}

	// recursively "splits" the array in two until left == right
	// sorts the two halves by merging them back together (using tempArray and
	// comparator) after each recursive call
	private static <E> void mergeSort(E[] array, E[] tempArray,
			Comparator<E> comparator, int left, int right) {
		if (left < right) {
			Integer center = (left + right) / 2;
			mergeSort(array, tempArray, comparator, left, center);
			mergeSort(array, tempArray, comparator, center + 1, right);
			merge(array, tempArray, comparator, left, center + 1, right);
		}
	}

	// merges the two halves of array into tempArray using pointers at indeces
	// left and right, comparing using comparator and ending when
	// left == leftEnd and right == rightEnd
	// updates array with the tempArray
	private static <E> void merge(E[] array, E[] tempArray,
			Comparator<E> comparator, int left, int right, int rightEnd) {
		int leftEnd = right - 1;
		int temp = left;
		int numElements = rightEnd - left + 1;
		// uses pointers on the two halves of the array and compares the
		// two values at the pointers using comparator
		// if the value at the right pointer is less than the value at the
		// left pointer it is added to the tempArray and right pointer moves
		// up one index
		// otherwise vice versa
		// runs until left == leftEnd or right == rightEnd
		while (left <= leftEnd && right <= rightEnd) {
			if (comparator.compare(array[left], array[right]) <= 0) {
				tempArray[temp] = array[left];
				left++;
			} else {
				tempArray[temp] = array[right];
				right++;
			}
			temp++;
		}
		// if there are still un-added values to the tempArray from the left
		// half of array, adds the rest of those values in
		while (left <= leftEnd) {
			tempArray[temp] = array[left];
			temp++;
			left++;
		}
		// if there are still un-added values to the tempArray from the right
		// half of array, adds the rest of those values in
		while (right <= rightEnd) {
			tempArray[temp] = array[right];
			temp++;
			right++;
		}
		// starting from rightEnd of array, set index value equal to tempArray's
		// value at same index
		// repeats at previous indeces until i == numElements
		for (int i = 0; i < numElements; i++) {
			array[rightEnd] = tempArray[rightEnd];
			rightEnd--;
		}
	}

}
