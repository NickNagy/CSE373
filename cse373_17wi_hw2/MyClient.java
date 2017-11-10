// Nick Nagy, CSE 373, Section AA, Chloe Lathe

// This class can be used to store and retrieve
// data in order of highest to lowest priority

import java.util.ArrayList;

public class MyClient {

	public static void main(String[] args) {
		PriorityQueue testQ = new ThreeHeap();
		System.out.println(testQ.toString());
		
		// tests the insert, toString, isEmpty, findMin and deleteMin methods
		testQ.insert(6);
		System.out.println(testQ.toString());
		testQ.insert(1);
		System.out.println(testQ.toString());
		testQ.insert(36);
		System.out.println(testQ.toString());
		testQ.insert(5);
		System.out.println(testQ.toString());
		testQ.insert(4);
		System.out.println(testQ.toString());
		testQ.insert(3);
		System.out.println(testQ.toString());
		testQ.insert(2);
		System.out.println(testQ.toString());
		testQ.insert(2);
		System.out.println(testQ.toString());
		testQ.insert(2);
		System.out.println(testQ.toString());
		testQ.insert(1);
		System.out.println(testQ.toString());
		System.out.println("----------------------");
		while(!testQ.isEmpty()){
			System.out.println("Min = " + testQ.findMin());
			testQ.deleteMin();
			System.out.println(testQ.toString());
		}
		
		// tests the buildQueue method with the same elements in the same order
		// as above
		// tests makeEmpty()
		System.out.println("----------------------");
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(6.0);
		list.add(1.0);
		list.add(36.0);
		list.add(5.0);
		list.add(4.0);
		list.add(3.0);
		list.add(2.0);
		list.add(2.0);
		list.add(2.0);
		list.add(1.0);
		testQ.buildQueue(list);
		System.out.println(testQ.toString());
		System.out.println("----------------------");
		testQ.makeEmpty();
		System.out.println(testQ.toString());
		
		// below should not run
		
		// System.out.println(testQ.findMin());
		// testQ.deleteMin();
	}
}
