#include <iostream>
#include "Time.h"

int main() {
	
	Time time1;
	Time time2{23, 59, 59};
	std::cout << time1 << std::endl;
	std::cout << time2 << std::endl;
	return 0;
}