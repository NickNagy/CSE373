// Nick Nagy, CSE 373, Section AA, Chloe Lathe

package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort Integers.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class IntegerSorter {
	/**
	 * Sorts the given array of integers in ascending order according to the
	 * comparator using mergesort. You may create as many private helper
	 * functions as you wish to implement this method.
	 * 
	 * A note about ascending order:
	 * 
	 * When the method is finished, it should be true that:
	 * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	 * array.length.
	 * 
	 * @param array
	 *            the integers to sort
	 * @param comparator
	 *            The comparator the will be used to compare two integers.
	 */
	public static void mergeSort(Integer[] array, Comparator<Integer> comparator) {
		GenericSorter.mergeSort(array, comparator);
	}

	/**
	 * Sort the array of integers in ascending order according to the comparator
	 * using selection sort.
	 * 
	 * A note about ascending order:
	 * 
	 * When the method is finished, it should be true that:
	 * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	 * array.length.
	 * 
	 * @param array
	 *            the array of integer that will be sorted.
	 * @param comparator
	 *            The comparator the will be used to compare two integers.
	 */
	public static void selectionSort(Integer[] array,
			Comparator<Integer> comparator) {
		GenericSorter.selectionSort(array, comparator);
	}

	/**
	 * Sort the array of integers in ascending order according to the comparator
	 * using insertion sort.
	 * 
	 * A note about ascending order:
	 * 
	 * When the method is finished, it should be true that:
	 * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	 * array.length.
	 * 
	 * @param array
	 *            the array of integers that will be sorted.
	 * @param comparator
	 *            The comparator the will be used to compare two integer.
	 */
	public static void insertionSort(Integer[] array,
			Comparator<Integer> comparator) {
		GenericSorter.insertionSort(array, comparator);
	}
}
