/*
 * Identification.cpp
 *
 *  Created on: Apr 11, 2017
 *      Author: broihier
 */

#include "Identification.h"


Identification::Identification(std::string category) : DatabaseComponent::DatabaseComponent(category+".testDbID") {

}

Identification::~Identification() {
	// TODO Auto-generated destructor stub
}

std::vector<std::string> Identification::getIdentificationIds() {
	return getIds();
}

std::string Identification::getIdentificationText(std::string id) {
	return getComponentText(id);
}

