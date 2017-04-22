/*
 * DatabaseComponent.cpp
 *
 *  Created on: Apr 11, 2017
 *      Author: broihier
 */

#include "DatabaseComponent.h"
#include <stdio.h>
#include <iostream>
#include <fstream>
#include "Utilities.h"
#include <vector>
#include <string>

DatabaseComponent::DatabaseComponent(std::string componentFilePath) {
	std::ifstream * DbFile= new std::ifstream();
	DbFile->open(componentFilePath);
	if (!DbFile->is_open()) {
		std::cout <<"file open error: " + componentFilePath << std::endl;
		Utilities::dumpStack();
		exit(-1);
	}
	char buffer[32767];
	do {
		DbFile->getline(buffer,sizeof(buffer));
		if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
			std::string id = std::string(buffer);
			std::vector<std::string> parts = Utilities::split(id,":");
			std::string trimmedId = Utilities::trim(parts[0]);
			ids.push_back(trimmedId);
			if (parts.size() > 1) {
				traceKeys[trimmedId] = parts[1];
			}
			DbFile->getline(buffer,sizeof(buffer));
			if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
				componentText[trimmedId] = std::string(buffer);
			}
		}
	} while (!DbFile->eof());
	DbFile->close();
	delete DbFile;
}

DatabaseComponent::~DatabaseComponent() {
}

const std::vector<std::string>& DatabaseComponent::getIds() const {
	return ids;
}
const std::string& DatabaseComponent::getComponentText(const std::string& id) const {
	std::map<std::string,std::string>::const_iterator iter = componentText.find(id);
	return iter->second;
}
const std::string& DatabaseComponent::getTraceKeys(const std::string& id) const {
	std::map<std::string,std::string>::const_iterator iter = traceKeys.find(id);
	return iter->second;
}
const std::map<std::string,std::string>& DatabaseComponent::getAllTraceKeys() const {
	return traceKeys;
}

