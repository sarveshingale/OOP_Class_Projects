#include <iostream>
#include <vector>
#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"

int main() {
	
	std::vector<Shape*>* shapes = new std::vector<Shape*>{};
	shapes -> push_back(new Rectangle{2.0, 4.0});
	shapes -> push_back(new Circle{2.0});
	
	for(Shape* shape : *shapes) {
		std::cout << shape -> name() << std::endl;
	}
	
	return 0;
}