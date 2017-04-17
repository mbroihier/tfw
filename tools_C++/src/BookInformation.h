/*
 * BookInformation.h
 *
 *  Created on: Apr 15, 2017
 *      Author: broihier
 */

#ifndef BOOKINFORMATION_H_
#define BOOKINFORMATION_H_

#include <string>
#include <vector>

class BookInformation {
private:
	std::string projectName;
	std::string projectAcronym = "undefined project acronym";
	std::vector<std::string> chapters;

public:
	BookInformation(std::string bookPath);
	virtual ~BookInformation();
	std::string getProjectName();
	std::string getProjectAcronym();
	std::vector<std::string> getChapters();
};

#endif /* BOOKINFORMATION_H_ */
