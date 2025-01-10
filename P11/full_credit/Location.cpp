#include "Location.h"

Location::Location(std::string filename, int line)
	: _filename{filename}, _line{line} {}

// First ordered by filename then by line number
int Location::compare(const Location& location) const {
	if (_filename > location._filename) {return 1;}
	else if(_filename < location._filename) {return -1;}
	else if(_line > location._line) {return 1;}
	else if(_line < location._line) {return -1;}
	else {return 0;}
}

std::ostream& operator<<(std::ostream& ost, const Location& location) {
	ost << location._filename << " line " << location._line;
	return ost;
}