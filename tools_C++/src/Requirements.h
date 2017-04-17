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
public:
	Requirements(std::vector<std::string> ids);
	virtual ~Requirements();
	std::string getRequirementsText(std::string id);
	std::map<std::string,std::string> getRequirements();
};

#endif /* REQUIREMENTS_H_ */
