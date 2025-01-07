#ifndef __TIME_H
#define __TIME_H

#include <iostream>

class Time {
	
	public:
		Time(int hour, int minute, int second);
		Time();
		Time operator+(Time time);
		Time operator+(int seconds) const;
		friend Time operator+(int seconds, const Time& time);
		Time& operator++();
		Time operator++(int value);
		int operator[](int index);
		inline bool operator==(Time& time) {return compare(time) == 0;};
		inline bool operator!=(Time& time) {return compare(time) != 0;};
		inline bool operator>(Time& time) {return compare(time) == 1;};
		inline bool operator<(Time& time) {return compare(time) == -1;};
		inline bool operator<=(Time& time) {int val = compare(time); return (val == -1 || val == 0);};
		inline bool operator>=(Time& time) {int val = compare(time); return (val == 1 || val == 0);};
		friend std::ostream& operator<<(std::ostream& ostream, const Time& time);
		friend std::istream& operator>>(std::istream& istream, Time& time);
		
	
	private:		
		int _hour;
		int _minute;
		int _second;
		int compare(Time time); // Assists the bool operators
		void rationalize(); // Converts time to correct format
};

#endif