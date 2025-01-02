#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>

int main(int argc, char* argv[]) {
	
	std::vector<std::string> caps{};
	std::vector<std::string>* no_caps = new std::vector<std::string>{};
	
	// Storing the vectors
	for(int i = 1; i < argc; i++) {
		std::string token = argv[i];
		if(isupper(token[0])) {
			caps.push_back(token);
		}
		else {
			no_caps -> push_back(token);
		}
	}
	
	// Sorting the vectors
	std::sort(caps.begin(), caps.end());
	// Printing the vectors
	std::cout << "Caps" << std::endl;
	for(auto word : caps) {
		std::cout << word << std::endl;
	}
	std::cout << "\n\nNo Caps" << std::endl;
	for(auto word : *no_caps) {
		std::cout << word << std::endl;
	}
	return 0;
}