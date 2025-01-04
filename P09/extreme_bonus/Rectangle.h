#ifndef __RECTANGLE_H
#define __RECTANGLE_H

#include "Shape.h"

class Rectangle : public Shape {
	
	public:
		Rectangle(double height, double width);
		std::string name() override;
		double area() override;
		void display_image() override;
		
	private:
		double _height;
		double _width;
};

#endif