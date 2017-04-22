/*
 * Procedures.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "Procedures.h"

Procedures::Procedures(std::string category): DatabaseComponent(category+".testDbProcedures") {
}

Procedures::~Procedures() {
}

const std::string& Procedures::getProcedureText(const std::string& id) const{
	return getComponentText(id);
}

