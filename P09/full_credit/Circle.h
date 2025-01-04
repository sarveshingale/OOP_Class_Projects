#ifndef __CIRCLE_H
#define __CIRCLE_H

class Circle {
	
	public:
		Circle(double radius);
		std::string name();
		double area();
		
	private:
		double _radius;
};

#endif