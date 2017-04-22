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

const std::string& Setup::getSetupText(const std::string& id) const{
	return getComponentText(id);
}

