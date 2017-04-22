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

	const std::string& getObjectiveText(const std::string& id) const;
	const std::string& getRequirementsInThisTest(const std::string& id) const;
};

#endif /* OBJECTIVE_H_ */
