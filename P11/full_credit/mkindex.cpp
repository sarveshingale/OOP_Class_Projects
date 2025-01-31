#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include "Index.h"

int main(int argc, char* argv[]) {
	
	
	std::string file_line; // Stores a line in file
	Index index{};
	std::cout << "Index" << std::endl << "=====\n\n" << std::endl;
	
	for(int i = 1; i < argc; i++) {
		std::ifstream ifts{argv[i]};
		try {
			if(!ifts) { 
				throw std::runtime_error{"Cant open input file: " + std::string{argv[i]}};
			}
		}
		catch(const std::exception& e) {
			std::cerr << e.what() << std::endl;
			continue;
		}
		int line = 1;
		while(ifts) {
			std::getline(ifts, file_line);
			std::istringstream iss{file_line};
			
			while(iss) {
				std::string word;
				iss >> word;
				if(!word.empty()) {
					if(!isalpha(word.front())) {
						word.erase(0, 1);
					}
					if(!isalpha(word.back())) {
						word.pop_back();
					}
					
					for(auto& c : word) {				
						if(isupper(c)) {
							c = tolower(c);
						}
					}
					index.add_word(word, argv[i], line);
				}
			}
			line++;
		}
	}
	
	std::cout << index << std::endl;
			
	return 0;
}