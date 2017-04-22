/*
 * Objective.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "Objective.h"

Objective::Objective(std::string category): DatabaseComponent(category+".testDbObjective") {

}

Objective::~Objective() {
}

const std::string& Objective::getObjectiveText(const std::string& id) const{
	return getComponentText(id);
}
const std::string& Objective::getRequirementsInThisTest(const std::string& id) const{
	return getTraceKeys(id);
}

