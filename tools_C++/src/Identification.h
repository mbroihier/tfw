/*
 * Identification.h
 *
 *  Created on: Apr 11, 2017
 *      Author: broihier
 */

#ifndef IDENTIFICATION_H_
#define IDENTIFICATION_H_

#include "DatabaseComponent.h"
#include <vector>
#include <string>

class Identification: public DatabaseComponent {
public:
	Identification(std::string category);
	~Identification();
	std::vector<std::string> getIdentificationIds();
	std::string getIdentificationText(std::string id);
};

#endif /* IDENTIFICATION_H_ */
