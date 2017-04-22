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
	virtual ~DatabaseComponent();
	const std::vector<std::string>& getIds() const;
	const std::string& getComponentText(const std::string& id) const;
	const std::string& getTraceKeys(const std::string& id) const;
	const std::map<std::string,std::string>& getAllTraceKeys() const;
};

#endif /* DATABASECOMPONENT_H_ */
