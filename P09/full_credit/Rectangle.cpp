#include "Rectangle.h"

Rectangle::Rectangle(double height, double width)
	: _height{height}, _width{width} {};

std::string Rectangle::name() {
	return "Rectangle with dimensions: " + std::to_string(_height) + "x" + std::to_string(_width);
}

double Rectangle::area() {
	return _width * _height;
}