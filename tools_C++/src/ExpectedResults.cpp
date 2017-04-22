/*
 * ExpectedResults.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "ExpectedResults.h"

ExpectedResults::ExpectedResults(std::string category): DatabaseComponent(category+".testDbExpectedResults") {
}

ExpectedResults::~ExpectedResults() {
}

const std::string& ExpectedResults::getExpectedResultsText(const std::string& id) const{
	return getComponentText(id);
}
