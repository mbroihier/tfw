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

std::string ExpectedResults::getExpectedResultsText(std::string id){
	return getComponentText(id);
}
