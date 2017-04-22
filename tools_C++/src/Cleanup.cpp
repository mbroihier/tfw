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

const std::string& Cleanup::getCleanupText(const std::string& id) const {
	return getComponentText(id);
}
