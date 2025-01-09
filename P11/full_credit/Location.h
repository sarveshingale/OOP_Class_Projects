#ifndef __LOCATION_H
#define __LOCATION_H

#include <iostream>


class Location {
	
	public:
		Location(std::string filename, int line);
		
		// First ordered by filename and then ordered by line #
		inline bool operator==(const Location& location) const {return compare(location) == 0;};
		inline bool operator!=(const Location& location) const {return compare(location) != 0;};
		inline bool operator>=(const Location& location) const {return (compare(location) == 1 || compare(location) == 0);};
		inline bool operator<=(const Location& location) const {return (compare(location) == -1 || compare(location) == 0);};
		inline bool operator>(const Location& location) const {return compare(location) == 1;};
		inline bool operator<(const Location& location) const {return compare(location) == -1;};
		friend std::ostream& operator<<(std::ostream& ost, Location& location);
		
	private:
		std::string _filename;
		int _line;
		int compare(const Location& location) const;
};

#endif