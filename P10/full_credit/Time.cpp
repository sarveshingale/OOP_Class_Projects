#include "Time.h"
#include <iomanip>

Time::Time(int hour, int minute, int second) 
	: _hour{hour}, _minute{minute}, _second{second} {
		rationalize();
}

std::ostream&  operator<<(std::ostream& ostream, const Time& time) {
	ostream << std::setfill('0') << std::setw(2) << time._hour << ':';
	ostream << std::setfill('0') << std::setw(2) << time._minute << ':';
	ostream << std::setfill('0') << std::setw(2) << time._second;
	return ostream;
}


void Time::rationalize() {
	
	// Correct seconds
	if(_second > 59) {		
		int add_minutes = _second / 60;
		_second = _second % 60;
		_minute += add_minutes;
	}
	// Correct minutes
	if(_minute > 59) {
		int add_hours = _minute / 60;
		_minute = _minute % 60;
		_hour += add_hours;
	}
	// Correct hours
	if(_hour > 23) {
		int multiplier = _hour / 24;
		_hour -= multiplier*24;		
	}
}
	
	

