#include "Location.h"

std::string Location::last_filename; // static field

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
	
	if(location._filename != Location::last_filename) {
		ost << location._filename << " line " << location._line;
	}
	else {
		ost << location._line;
	}
	Location::last_filename = location._filename;
	return ost;
}