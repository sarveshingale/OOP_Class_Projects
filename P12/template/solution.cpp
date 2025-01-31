#include <iostream>
#include <vector>
#include <cmath>

// Make this class generic!
// The Makefile assumes you'll copy this file to solution.cpp
//    and implement your solution there.
template<class T>
class Top3 {
    T _first;
    T _second;
    T _third;
    int elements = 0;
  public:
    void add(T x) {
        if(elements++ == 0) {
            _first = x;
            _second = x;
            _third = x;
        } else {
            if(_first < x) {
                _third = _second;
                _second = _first;
                _first = x;
            } else if (_second < x) {
                _third = _second;
                _second = x;
            } else if (_third < x) {
                _third = x;
            }
        }
    }
    T first() {return _first;}
    T second() {return _second;}
    T third() {return _third;}
};

// (Comment out or modify this main function once Top3 is generic)
/*
int main() {
    std::vector<int> v {13, 27, 48, -9, -888, 42, 36, 11};
    Top3 t3;
    for(auto& e : v) {
        std::cout << e << " ";
        t3.add(e);
    }
    std::cout << "\n\nTop 3 elements are \n  "
              << t3.first() << "\n  "
              << t3.second() << "\n  "
              << t3.third() << std::endl;
}*/

// The main function below uses your *generic* Top3 to find the
//     Triple objects with the top 3 magnitudes.
// You may need additional constructors, methods, functions,
//     and / or operators to meet duck typing requirements.

class Triple {
    int _x;
    int _y;
    int _z;
  public:
    Triple(int x = 0, int y = 0, int z = 0)
        : _x{x}, _y{y}, _z{z} { }
    double magnitude() const {
        return sqrt((double) (_x*_x + _y*_y + _z*_z));
    }
	bool operator<(const Triple& t) {
        return magnitude() < t.magnitude();
    }
    friend std::ostream& operator<<(std::ostream& ost, const Triple& t) {
        return ost << "(" << t._x << "," << t._y << "," << t._z << ")";
    }
};

// Use this main function to test your solution
//   (be sure to comment out the other main function above!)


int main() {
    std::vector<Triple> v {
        Triple{5,4,3},
        Triple{1,0,0},
        Triple{0,0,0},
        Triple{3,2,1},
        Triple{7,6,5},
        Triple{4,3,2},
        Triple{2,1,0},
        Triple{6,5,4}
    };
    Top3<Triple> t3;
    for(auto& e : v) {
        std::cout << e << " magnitude " << e.magnitude() << std::endl;
        t3.add(e);
    }
    std::cout << "\n\nTop 3 elements are \n  "
              << t3.first() << "\n  "
              << t3.second() << "\n  "
              << t3.third() << std::endl;
}
