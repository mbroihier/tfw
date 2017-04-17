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

std::string Objective::getObjectiveText(std::string id){
	return getComponentText(id);
}
std::string Objective::getRequirementsInThisTest(std::string id){
	return getTraceKeys(id);
}

