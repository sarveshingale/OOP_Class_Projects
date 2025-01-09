#include <iostream>

class Location {
	
	public:
		Location(std::string filename, int line);
		inline bool operator==(Location& location) const {compare()};
		inline bool operator!=(Location& location) const {compare()};
		inline bool operator>=(Location& location) const {compare()};
		inline bool operator<=(Location& location) const {compare()};
		inline bool operator>(Location& location) const {compare()};
		inline bool operator<(Location& location) const {compare()};
		friend ostream& operator<<(ostream& ost, Location& location);
		
	private:
		std::string _filename;
		int _line;
		int compare(Location& location);
}