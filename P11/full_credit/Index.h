#ifndef __INDEX_H
#define __INDEX_H

#include <iostream>

class Index {
	
	public:
		void add_word(Word word, std::string filename, int line);
		friend ostream& operator<<(ostream& ost, Index& index);
	
	private:
		typedef std::string Word;
		typedef std::set<Location> Locations;
		std::map<Word, Locations> _index;
};

#endif		