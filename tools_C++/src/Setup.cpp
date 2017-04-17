/*
 * Setup.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "Setup.h"

Setup::Setup(std::string category): DatabaseComponent(category+".testDbPre") {
}

Setup::~Setup() {
}

std::string Setup::getSetupText(std::string id){
	return getComponentText(id);
}

