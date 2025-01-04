#include "Circle.h"
#include <cmath>
#include <iostream>
Circle::Circle(double radius)
	: _radius{radius} {}

std::string Circle::name() {
	return "Circle with radius " + std::to_string(_radius);
}

double Circle::area() {
	return M_PI * _radius * _radius;
}

void Circle::display_image() {
	std::cout << std::endl;
}