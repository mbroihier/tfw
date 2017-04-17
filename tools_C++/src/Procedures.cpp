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

std::string Procedures::getProcedureText(std::string id){
	return getComponentText(id);
}

