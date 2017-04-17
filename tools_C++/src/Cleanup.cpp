/*
 * Cleanup.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "Cleanup.h"

Cleanup::Cleanup(std::string category): DatabaseComponent(category+".testDbPost") {
}

Cleanup::~Cleanup() {
}

std::string Cleanup::getCleanupText(std::string id){
	return getComponentText(id);
}
