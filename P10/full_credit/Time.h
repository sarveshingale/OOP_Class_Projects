#ifndef __TIME_H
#define __TIME_H

#include <iostream>

class Time {
	
	public:
		Time(int hour, int minute, int second);
		Time operator+(Time time);
		Time& operator++();
		Time operator++(int value);
		inline bool operator==(Time& time) {return compare()};
		inline bool operator!=(Time& time) {return compare()};
		inline bool operator>(Time& time) {return compare()};
		inline bool operator<(Time& time) {return compare()};
		inline bool operator<=(Time& time) {return compare()};
		inline bool operator>=(Time& time) {return compare()};
		friend std::ostream& operator<<(ostream& ostream, Time& time);
		friend std::istream& operator>>(istream& istream, Time& time);
		
	
	private:		
		int _hour;
		int _minute;
		int _second;
		int compare(Time time);
		void rationalize();
};

#endif