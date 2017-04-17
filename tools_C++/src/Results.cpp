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

std::string Results::getResultsText(std::string id){
	return getComponentText(id);
}

