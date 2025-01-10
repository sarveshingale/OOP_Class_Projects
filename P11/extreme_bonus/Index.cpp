#include "Index.h"

void Index::add_word(Word word, std::string filename, int line) {
	
	for(auto& [key, value] : _index) {
		if(key == word && value == Location{filename, line}) {
			return;
		}
	}
	_index.insert({word, Location{filename, line}});
	
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
	
	/*
	for(auto& [key, value_set] : index._index) {
		ost << key << ": ";
		Location::next_word();
		std::set<Location>::iterator it;
		std::string separator;
		for(it = value_set.begin(); it != value_set.end(); it++) {
			ost << separator << *it;
			separator = ", ";
		}
		
		ost << std::endl;
	}*/
	//std::string separator;
	std::string last_word;
	for(auto& [key, value] : index._index) {
		if(last_word == key) {
			ost << ", " << value;
			
		}
		else {
			ost << std::endl;
			ost << key << ": ";
			Location::next_word();
			ost << value;
			last_word = key;
		}
	}
	
	return ost;	
}
