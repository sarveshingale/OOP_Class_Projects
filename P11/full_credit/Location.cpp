#include "Location.h"

Location::Location(std::string filename, int line)
	: _filename{filename}, _line{line} ();

// First ordered by filename then by line number
void Location::compare(Location& location) {
	if (_filename > location._filename) {return 1;};
	else if(_filename < location._filename {return -1;};
	else if(_line > location.line) {return 1;};
	else if(_line < location.line) {return -1;};
	else {return 0;};
}

ostream& Location::operator<<(ostream& ost, Location& location) {
	ost << _filename << " line " << _line;
	return ost;
}