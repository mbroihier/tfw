/*
 * Objective.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef OBJECTIVE_H_
#define OBJECTIVE_H_

#include "DatabaseComponent.h"
#include <vector>
#include <string>

class Objective: public DatabaseComponent {
public:
	Objective(std::string category);
	virtual ~Objective();

	std::string getObjectiveText(std::string id);
	std::string getRequirementsInThisTest(std::string id);
};

#endif /* OBJECTIVE_H_ */
