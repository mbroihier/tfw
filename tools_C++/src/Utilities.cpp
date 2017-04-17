/*
 * Utilities.cpp
 *
 *  Created on: Apr 13, 2017
 *      Author: broihier
 */

#include "Utilities.h"
#include <execinfo.h>
#include <unistd.h>
#include <stdlib.h>



Utilities::Utilities() {
}

Utilities::~Utilities() {
}
std::vector<std::string> Utilities::split(std::string sourceString, std::string delimiter){
	const char * cSourceString = sourceString.c_str();
	const char * cDelimiter = delimiter.c_str();

	std::vector<std::string> segments;

	const char * currentLocation = cSourceString;
	const char * delimiterLocation = 0;

	bool done = false;

	do {
		delimiterLocation = strstr(currentLocation,cDelimiter);
		if (!delimiterLocation) {
			done = true;
			delimiterLocation = cSourceString + strlen(cSourceString);
		}
		std::string substring = "";
		while (currentLocation != delimiterLocation) {
			substring += *currentLocation++;
		}
		currentLocation += strlen(cDelimiter);
		segments.push_back(substring);
	} while (!done);
	return segments;
}
std::string Utilities::trim(std::string item) {
	std::string returnItem = "";
	for (char c : item) {
		if (c != ' ') {
			returnItem += c;
		}
	}
	return returnItem;
}

void Utilities::dumpStack() {
	void *stack[20];
	size_t size;
	size = backtrace(stack,20);
	backtrace_symbols_fd(stack,size,STDERR_FILENO);
}

