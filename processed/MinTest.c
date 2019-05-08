








//#if MIN(2, 3) > 1

//#if ((2) < (3) ? (3) : (2)) < 10
//#if 2 < 10
int a;


int main(){
	
	int a1 = 1;
	int a2 = 4;
	
	printf("MIN: %d - ", ({__typeof__(a1) _a = (a1); __typeof__(a2) _b = (a2); _a < _b ? _a : _b;}));
	
	printf("MAX: %d - ", ((a1) < (a2) ? (a2) : (a1)));
	
	
 
	int x = 10;
	int y = 5;
	int z = 4;
 
	// What happens now?
	if(x < 0)
		
	    { x ^= y; y ^= x; x ^= y; };
		
	else
	    { x ^= z; z ^= x; x ^= z; }; 
	
	
}

