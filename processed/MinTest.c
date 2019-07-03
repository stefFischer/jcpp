#include <stdio.h>

#define C 1

#ifdef DO_SWAP
#define MIN(a, b) ((a) < (b) ? (a) : (b))
#define B 2
#else  
#define MIN(a, b) ((a) == (b) ? (a) : (b))
#define B 5
#define C 0
#endif

#define MAX(a, b) ((a) < (b) ? (b) : (a))

#ifdef DO_SWAP
#define SWAP(a, b) { a ^= b; b ^= a; a ^= b; }
#endif

#define ADD(a, b) (a + b)

#if 1


#define A 2

#endif

#if ((!DO_SWAP && 1) || ((DO_SWAP && !(!DO_SWAP)) && 1))

int a;

#elif 0
int b;
#else
#if !(!DO_SWAP)
int c;
#endif
#endif

#if ((((!DO_SWAP && 0) || ((DO_SWAP && !(!DO_SWAP)) && 1)) || (((DO_SWAP && !(!DO_SWAP)) && !DO_SWAP) && 1)) || ((!DO_SWAP && (DO_SWAP && !(!DO_SWAP))) && 1))
int min;
#endif

#if NUMBER > 5 && ((NUMBER) < 5 ? 5 : (NUMBER)) > 5

#endif

#undef SWAP

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
		#if ((DO_SWAP && 0) && 0 && defined(DO_SWAP))
	    SWAP(x, y);
		#endif
	else
	    SWAP(x, z); 
	
	#endif
	
	#ifndef DO_SWAP
	//no swaping
	#endif
	
	#if 97
	
	#endif
}

