#include <iostream>
#include <fstream>
#include <vector>
#include "Index.h"

int main(int argc, char* argv[]) {
	
	
	std::vector<std::string> words{}; // Stores a line in the file
	Index index{};
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
			std::getline(ifts, words, ' ');
			for(auto& word : words) {
				for(auto& c : word) {
					if(!isalpha(c)) {
						c = ' ';
					}
					if(isupper(c)) {
						c = tolower(c);
					}
				}
				index.add_word(word, argv[i], line);
			}
			line++;
		}
	}
			
	return 0;
}