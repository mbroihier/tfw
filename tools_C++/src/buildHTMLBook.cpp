//============================================================================
// Name        : tools_C++.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <stdio.h>
#include "pugixml.hpp"
#include "DOMizeTemplate.h"
#include "TestCase.h"
#include "TestDatabase.h"
#include "Book.h"

using namespace std;
using namespace pugi;

int main(const int argc, const char * argv[]) {

	cout << "processing " << argv[1] << endl;
	Book book(argv[1]);
	return 0;

}

