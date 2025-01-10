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
		for(auto& value : value_set) {
			ost << value;
		}
		ost << std::endl;
	}
	return ost;
}
		