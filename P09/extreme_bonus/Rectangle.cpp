#include "Rectangle.h"
#include <iostream>

Rectangle::Rectangle(double height, double width)
	: _height{height}, _width{width} {};

std::string Rectangle::name() {
	return "Rectangle with dimensions: " + std::to_string(_height) + "x" + std::to_string(_width);
}

double Rectangle::area() {
	return _width * _height;
}

void Rectangle::display_image() {
	
	for(int i = 0; i < _height; i++) {
		for(int j = 0; j < _width; j++) {
			std::cout << "*";
		}
		std::cout << std::endl;
	}
}

