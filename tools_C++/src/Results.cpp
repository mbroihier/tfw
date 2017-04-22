/*
 * Results.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "Results.h"

Results::Results(std::string category): DatabaseComponent(category+".testDbResults") {
}

Results::~Results() {
}

const std::string& Results::getResultsText(const std::string& id) const{
	return getComponentText(id);
}

