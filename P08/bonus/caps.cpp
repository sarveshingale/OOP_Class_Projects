#include <iostream>
#include <cstring>
#include <vector>

int main(int argc, char* argv[]) {
	
	std::vector<std::string> caps{};
	std::vector<std::string>* no_caps = new std::vector<std::string>{};
	
	for(int i = 1; i < argc; i++) {
		std::string token = argv[i];
		if(isupper(token[0])) {
			caps.push_back(token);
		}
		else {
			no_caps -> push_back(token);
		}
	}
	
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