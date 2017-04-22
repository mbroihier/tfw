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
	const std::vector<std::string>& getIdentificationIds() const;
	const std::string& getIdentificationText(std::string id) const;
};

#endif /* IDENTIFICATION_H_ */
