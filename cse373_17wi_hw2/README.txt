Nick Nagy, CSE 373, Section AA, Chloe Lathe

1. In order to make sure that I can see everything happening to the ThreeHeap while I'm in my client class I created a toString() method for the ThreeHeap. Originally I implemented the toString() to print all indexes of the heapArray so I could test that it was resizing properly, but after confirming that was working I changed the method to only print the elements of the PriorityQueue. I started testing insert() by adding elements one-by-one to the PriorityQueue, printing toString() each time to see where elements were ending up, and then used the same approach for isEmpty(), findMin() and deleteMin() by removing the elements one-by-one until the PriorityQueue was empty again. I added all the same elements a second time by adding them first to an ArrayList, then using the buildQueue method with this list and printing the toString() at the end. I then used makeEmpty() to empty the PriorityQueue() and tried calling findMin() and deleteMin() to make sure the exceptions were being thrown.

2. Runtimes:
	isEmpty() -> O(1)
	size() -> O(1)
	findMin() -> O(1)
	
	The above three methods are all O(1) runtime because their operations are independent of the size of the heap.

	insert() -> O(logN + O(1)amortized) = O(logN)
	deleteMin() -> O(logN + O(1)amortized) = O(logN)

	These are both O(logN) because every iteration will look at one of three possible children and percolate, so only a third of the elements at each level are ever visited. Continuously dividing by three at every level gives a base-3 logarithmic function. Resizing is O(1) amortized because the number of times the array has to resize is proportional to the number of elements stored.

	buildQueue() -> O(N)

	Each element is added linearly to the heap before any swapping, so this is just constant O(N). When swapping, only about a third of the total elements will be considered (the "leaf nodes" of the heap don't have to be compared with their children). Each level higher up the elements will have to potentially percolate 1 extra time, but the number of elements at each level that will have to percolate has become a third of the number of elements at the level before it. This process remains true independent of the size of the array, which means that percolating will be O(1).

3. a) O(NlogN) runtime. The outermost for loop will run N number of times, and at each step of this loop j will double in size until it
reaches N. We can think of this as j's total number of iterations halving each time, so there will be logN iterations in the inner loop
for each iteration through the outer loop (logN for N number of times). When analyzing runtime we look at worst-case, so we ignore the condition
where the loop will break.

b) O(N^3) runtime. When analyzing runtime we drop constants and the non-dominant terms. For example, j will iterate linearly until ((n^2 + n)/3) is reached, but because n^2 is far greater than n asymptotically, and at extreme values of n the (1/3) factor is irrelevant, this is simplified
to n^2. This O(N^2) loop will run N number of times (the outer for loop's run time).

c) O(N^2) runtime. The mysteryThree() method calls printCats() N number of times, and the loop in printCats() will run N number of times each time
it is called. This is N*N.

4. a) 	difference = 0
	for(i=0 to i=maxIndex-1, i++){
		for(j=i+1 to j=maxIndex, j++){
			if array(j) - array(i) > difference
			difference = array(j) - array(i)
		}
	}
	return difference

b) 	min = 255
	max = 0
	for(int i=0 to i=maxIndex-1, i++){
		if array(i) > max {
			max = array(i)
		}
		if arra(i) < min {
			min = array(i)
		}
	}
	return max-min

c) If the elements in the array are already sorted, then the max difference will always be array[maxIndex] - array[0]. If this is not guaranteed
to be true, some searching will have to be done, meaning that the runtime is not independent of the array size, and that O(1) is not possible.

5. a) To insert when space has run out will be O(N^2), and this will happen every 5 elements. Because the operation is quadratic but the frequency is linear, the relationship is disproportionate and therefore not amortized.

b) To insert when space has run out will be O(N^2), and this will happen every 1.5*N elements. Since space increases proportionally to the number of times it's resized, the frequency of resizing is logarithmic, which is proportional to the N^2 operation. The amortized cost is O(1) like the method I used.

c) To insert when space has run out will be O(N^2), and this will happen every 2*N elements. Since space keeps doubling the frequency of resizing is logarithmic, so the amortized cost is proportional and will be O(1). This is the same as the method I used.

6. To improve the efficiency of buildQueue() from O(NlogN) runtime to O(N) runtime I implemented Floyd's Algorithm. To save space in the PriorityQueue when deleting elements I would half the size of the array if only a fourth or less of its capacity was filled up. I also made
a toString() method to make it easy for the client to visualize what each method in the ThreeHeap class does.