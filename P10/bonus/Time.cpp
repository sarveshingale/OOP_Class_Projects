#include "Time.h"
#include <iomanip>
#include <cmath>

Time::Time(int hour, int minute, int second) 
	: _hour{hour}, _minute{minute}, _second{second} {
		rationalize();
}

Time::Time()
	: Time{0,0,0} {};

std::ostream&  operator<<(std::ostream& ostream, const Time& time) {
	ostream << std::setfill('0') << std::setw(2) << time._hour << ':';
	ostream << std::setfill('0') << std::setw(2) << time._minute << ':';
	ostream << std::setfill('0') << std::setw(2) << time._second;
	return ostream;
}

std::istream& operator>>(std::istream& istream, Time& time) {
	int hour, minute, second;
	char separator1, separator2;
	if(istream >> hour >> separator1 >> minute >> separator2 >> second) {
		if(separator1 == ':' && separator2 == ':') {
			time._hour = hour;
			time._minute = minute;
			time._second = second;
			time.rationalize();
		}
		else {
			istream.setstate(std::ios::failbit);
		}
	}
	else {
		istream.setstate(std::ios::failbit);
	}
	return istream;
}
	

Time Time::operator+(Time time) {
	return Time{_hour + time._hour, _minute + time._minute, _second + time._second};
}

Time Time::operator+(int seconds) const {
	return Time{_hour, _minute, _second + seconds};
}

Time operator+(int seconds, const Time& time) {
	return time + seconds;
}

Time& Time::operator++() {
	_second++;
	rationalize();
	return *this;
}

Time Time::operator++(int value) {
	Time time = *this; // time holds copy of the unmodified object
	_second++;
	return time;
}




void Time::rationalize() {
	
	// Correct seconds
	if(_second > 59) {		
		int add_minutes = _second / 60;
		_second = _second % 60;
		_minute += add_minutes;
	}
	if(_second < 0) {
		int sub_minutes = ceil(_second / 60);
		if(sub_minutes == 0) {sub_minutes++;}; // Need to subtract atleast 1 minute
		_second = 60 + (_second % 60);
		
		// Changing 60 to 0 because we did ceil
		if(_second == 60) {
			_second = 0;
		}
		_minute -= sub_minutes;
	}
	// Correct minutes
	if(_minute > 59) {
		int add_hours = _minute / 60;
		_minute = _minute % 60;
		_hour += add_hours;
	}
	if(_minute < 0) {
		int sub_hours = ceil(_minute / 60);
		if(sub_hours == 0) {sub_hours++;}; // Need to subtract atleast an hour
		_minute = 60 + (_minute % 60);
		
		// Changin 60 to 0 because we did ceil
		if(_minute == 60) {
			_minute = 0;
		}
		_hour -= sub_hours;
	}
	// Correct hours
	if(_hour > 23) {
		int multiplier = _hour / 24;
		_hour -= multiplier*24;		
	}
	if(_hour < 0) {
		int multiplier = ceil(_hour / 24);
		if(multiplier == 0) { multiplier++;}; // multiplier has to be atleast 1
		_hour = multiplier*24 + _hour;
	}
}

int Time::compare(Time time) {
	if(_hour < time._hour) {
		return -1;
	}
	else if(_hour > time._hour) {
		return 1;
	}
	else if(_minute < time._minute) {
		return -1;
	}
	else if(_minute > time._minute) {
		return 1;
	}
	else if(_second < time._second) {
		return -1;
	}
	else if(_second > time._second) {
		return 1;
	}
	else {
		return 0;
	}
}
	

