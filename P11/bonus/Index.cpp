#include "Index.h"

void Index::add_word(Word word, std::string filename, int line) {
	
	bool word_present = false;
	for(auto& [key, locations] : _index) {
		if(word == key) {
			word_present = true;
			break;
		}
	}
	
	if(!word_present) {
		_index.insert({word, {Location{filename, line}}});
	}
	else {
		 _index[word].insert(Location{filename, line});
	}
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
	
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
	}
	return ost;
}
