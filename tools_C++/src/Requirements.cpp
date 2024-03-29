/*
 * Requirements.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "Requirements.h"
#include <stdio.h>
#include <iostream>
#include <fstream>


Requirements::Requirements(std::vector<std::string> ids) {
	for (int index = 0; index < ids.size(); index++){
		std::string item = ids[index];
		if (requirements.find(item) == requirements.end() ){
			update(item);
		}
	}
}

Requirements::~Requirements() {
}

void Requirements::update(std::string r) {
	std::string path;
	if (r.find("-")) {
		path = r.substr(0,r.find("-"));
	} else {
		path = r;
	}
	std::ifstream * DbFile = new std::ifstream();
	DbFile->open("REQ."+path);
	if (!DbFile->is_open()) {
		std::cout << "file open error: REQ." + path
				<< std::endl;
		exit(-1);
	}
	char buffer[32767];
	do {
		std::string id;
		std::string text;
		DbFile->getline(buffer, sizeof(buffer));
		if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
			id = std::string(buffer);
			DbFile->getline(buffer, sizeof(buffer));
			if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
				text = std::string(buffer);
				requirements[id] = text;
			}
		}
	} while (!DbFile->eof());
	DbFile->close();

}

const std::string& Requirements::getRequirementsText(const std::string& id) const{

	if (requirements.find(id) != requirements.end()) {
		const std::map<std::string,std::string>::const_iterator iter = requirements.find(id);
		return iter->second;
	} else {
		return REQUIREMENTS_NOT_FOUND;
	}
}

const std::map<std::string,std::string>& Requirements::getRequirements() const{
	return requirements;
}
