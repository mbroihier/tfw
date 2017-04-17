/*
 * TestCase.h
 *
 *  Created on: Apr 13, 2017
 *      Author: broihier
 */

#ifndef TESTCASE_H_
#define TESTCASE_H_

#include "DOMizeTemplate.h"
#include "Utilities.h"
#include "pugixml.hpp"
#include "TestDatabase.h"
#include <vector>
#include <string>

using namespace pugi;

class TestCase {
private:
	DOMizeTemplate * DOMizedTemplate;
	std::string status;

public:
	TestCase(std::string id, std::string category, TestDatabase& database);
	virtual ~TestCase();
	std::string getStatus(void);
};

#endif /* TESTCASE_H_ */
