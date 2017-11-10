Nick Nagy, CSE 373, Section AA, Chloe Lathe

1. Quick-Sort *window has to be maximized*

CUTOFF: 3
[5,7,9,1,3,4,6,8,2]

Median of 5, 3, 2: 3
				3 7 9 1 5 4 6 8 2

CUTOFF reached --> 1 2			3			7 9 5 4 6 8
					Median of 7, 5, 8: 5
		   1 2			3			5 9 7 4 6 8

		   1 2			3		4 	     5	   	9 7 6 8
								Median of 9, 7, 8: 7
		   1 2			3		4	     5    	7 9 6 8

		   1 2			3		4	     5	    6      7	   9 8 <-- CUTOFF reached

		   1 2			3		4	     5	    6	   7       8 9

2. Radix Sort

INPUT: abc, da, ffff, defcd, abebd, ca, b, fef, dfe

_: abc, da, ffff, ca, b, fef, dfe
a:
b:
c:
d: defcd, abebd
e:
f:

OUTPUT: abc, da, ffff, ca, b, fef, dfe, defcd, abebd

INPUT: abc, da, ffff, ca, b, fef, dfe, defcd, abebd

_: abc, da, ca, b, fef, dfe
a:
b: abebd
c: defcd
d: 
e:
f: ffff

OUTPUT: abc, da, ca, b, fef, dfe, abebd, defcd, ffff

INPUT: abc, da, ca, b, fef, dfe, abebd, defcd, ffff

_: da, ca, b
a:
b:
c: abc,
d:
e: dfe, abebd
f: fef, defcd, ffff

OUTPUT: da, ca, b, abc, dfe, abebd, fef, defcd, ffff

INPUT: da, ca, b, abc, dfe, abebd, fef, defcd, ffff

_: b
a: da, ca
b: abc, abebd
c:
d:
e: fef, defcd
f: dfe, ffff

OUTPUT: b, da, ca, abc, abebd, fef, defcd, dfe, ffff

INPUT: b, da, ca, abc, abebd, fef, defcd, dfe, ffff

_:
a: abc, abebd
b: b
c: ca
d: da, defcd, dfe
e:
f: fef, ffff

OUTPUT: abc, abebd, b, ca, da, defcd, dfe, fef, ffff

3. I used a GenericSort class for the IntegerSort and PacketSort classes.

