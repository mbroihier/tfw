/*
 * DatabaseComponent.h
 *
 *  Created on: Apr 11, 2017
 *      Author: broihier
 */

#ifndef DATABASECOMPONENT_H_
#define DATABASECOMPONENT_H_

#include <vector>
#include <map>

class DatabaseComponent {
private:
	std::vector<std::string> ids;
	std::map<std::string,std::string> componentText;
	std::map<std::string,std::string> traceKeys;
public:
	DatabaseComponent(std::string componentFilePath);
	~DatabaseComponent();
	std::vector<std::string> getIds();
	std::string getComponentText(std::string id);
	std::string getTraceKeys(std::string id);
	std::map<std::string,std::string> getAllTraceKeys();
};

#endif /* DATABASECOMPONENT_H_ */
