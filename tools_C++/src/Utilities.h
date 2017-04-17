/*
 * Utilities.h
 *
 *  Created on: Apr 13, 2017
 *      Author: broihier
 */

#ifndef UTILITIES_H_
#define UTILITIES_H_

#include <vector>
#include <string>

class Utilities {
public:
	Utilities();
	virtual ~Utilities();
	static std::vector<std::string> split(std::string sourceString, std::string delimiter);
	static std::string trim(std::string sourceString);
	static void dumpStack();
};

#endif /* UTILITIES_H_ */
