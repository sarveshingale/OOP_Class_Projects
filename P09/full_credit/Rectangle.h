#ifndef __RECTANGLE_H
#define __RECTANGLE_H

class Rectangle {
	
	public:
		Rectangle(double height, double width);
		std::string name();
		double area();
		
	private:
		double _height;
		double _width;
};

#endif