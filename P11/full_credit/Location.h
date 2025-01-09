#ifndef __LOCATION_H
#define __LOCATION_H

#include <iostream>

class Location {
	
	public:
		Location(std::string filename, int line);
		
		// First ordered by filename and then ordered by line #
		inline bool operator==(Location& location) const {return compare() == 0};
		inline bool operator!=(Location& location) const {return compare() != 0};
		inline bool operator>=(Location& location) const {return (compare() == 1 || compare() == 0)};
		inline bool operator<=(Location& location) const {return (compare() == -1 || compare() == 0)};
		inline bool operator>(Location& location) const {return compare() == 1};
		inline bool operator<(Location& location) const {return compare() == -1};
		friend ostream& operator<<(ostream& ost, Location& location);
		
	private:
		std::string _filename;
		int _line;
		int compare(Location& location);
}

#endif