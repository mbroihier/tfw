/*
 * Requirements.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef REQUIREMENTS_H_
#define REQUIREMENTS_H_

#include <string>
#include <vector>
#include <map>

class Requirements {
private:
	std::map<std::string,std::string> requirements;
	void update(std::string r);
	const std::string REQUIREMENTS_NOT_FOUND = "Requirement text not found";

public:
	Requirements(std::vector<std::string> ids);
	virtual ~Requirements();
	const std::string& getRequirementsText(const std::string& id) const;
	const std::map<std::string,std::string>& getRequirements() const;
};

#endif /* REQUIREMENTS_H_ */
