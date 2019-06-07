#include <stdio.h>

#define MIN(a, b) ({__typeof__(a) _a = (a); __typeof__(b) _b = (b); _a < _b ? _a : _b;})

#define MAX(a, b) ((a) < (b) ? (b) : (a))

#define SWAP(a, b) { a ^= b; b ^= a; a ^= b; }

#define ADD(a, b) (a + b)

#define A 1


#if ((2) < (3) ? (3) : (2)) < 10

int a;

#elif ((2) < (((4) < (3) ? (3) : (4))) ? (((4) < (3) ? (3) : (4))) : (2)) < 3
int b;
#else
int c;
#endif

int main(){
	
	int a1 = 1;
	int a2 = 4;
	
	printf("MIN: %d - ", MIN(a1, a2));
	
	printf("MAX: %d - ", MAX(a1, a2));
	
	#ifdef DO_SWAP
 
	int x = 10;
	int y = 5;
	int z = 4;
 
	// What happens now?
	if(x < 0)
		#if defined(SWAP)
	    SWAP(x, y);
		#endif
	else
	    SWAP(x, z); 
	
	#endif
	
	#ifndef DO_SWAP
	//no swaping
	#endif
}

