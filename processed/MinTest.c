#include <stdio.h>

#define C 1

#if defined(DO_SWAP)
#define MIN(a, b) ((a) < (b) ? (a) : (b))
#define B 2
#else  
#define MIN(a, b) ((a) == (b) ? (a) : (b))
#define B 11
#define C 0
#endif

#define MAX(a, b) ((a) < (b) ? (b) : (a))

#if defined(DO_SWAP)
#define SWAP(a, b) { a ^= b; b ^= a; a ^= b; }
#endif

#define ADD(a, b) (a + b)

#if 1


#define A 2

#endif

#if defined(DO_SWAP)

int a;

#elif 0
int b;
#else
#if defined(DO_SWAP)
int c;
#endif
#endif

#if defined(DO_SWAP)
int min;
#endif

#if NUMBER > 5 && (NUMBER < 5 ? 5 : NUMBER) > 5

#endif

#if 0

#endif

#if !defined(DO_SWAP)
#undef SWAP
#define X 
#else
#define Y 
#endif

#if defined(DO_SWAP)
#endif

#if !defined(DO_SWAP)
#endif

#if defined(DO_SWAP)
#endif

#if !defined(DO_SWAP)
#endif

int main(){
	
	int a1 = 1;
	int a2 = 4;
	
	printf("MIN: %d - ", MIN(a1, a2));
	
	printf("MAX: %d - ", MAX(a1, a2));
	
	#if defined(DO_SWAP)
 
	int x = 10;
	int y = 5;
	int z = 4;
  
	// What happens now?
	if(x < 0)
		#if defined(DO_SWAP)
	    SWAP(x, y);
		#endif
		
		#if 0
		#endif
	else
	    SWAP(x, z); 
	
	#endif
	
	#if !defined(DO_SWAP)
	//no swaping
	#endif
	
	#if 97
	
	#endif
}

