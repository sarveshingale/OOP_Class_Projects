#ifndef __INDEX_H
#define __INDEX_H

#include <iostream>
#include <set>
#include <map>
#include "Location.h"

typedef std::string Word;

class Index {
	
	
	public:
		void add_word(Word word, std::string filename, int line);
		friend std::ostream& operator<<(std::ostream& ost, const Index& index);
		
	private:
		std::multimap<Word, Location> _index;
};

#endif		