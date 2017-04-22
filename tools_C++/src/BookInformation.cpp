/*
 * BookInformation.cpp
 *
 *  Created on: Apr 15, 2017
 *      Author: broihier
 */

#include "BookInformation.h"
#include "Utilities.h"
#include <stdio.h>
#include <iostream>
#include <fstream>
#include <regex>


BookInformation::BookInformation(std::string bookPath) {
	std::ifstream * DbFile= new std::ifstream();
	DbFile->open("PROJECT.testDb");
	if (!DbFile->is_open()) {
		std::cout <<"file open error: PROJECT.testDb" << std::endl;
		exit(-1);
	}
	projectName = "";
	projectAcronym = "";
	char buffer[32767];
	DbFile->getline(buffer,sizeof(buffer));
	if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
		projectName = std::string(buffer);
		std::smatch stringMatches;
		std::regex expression   ("[^\(]+.([^)]+).+");
		std::regex_match(projectName,stringMatches,expression);
		if (stringMatches.size() > 0) {
			projectAcronym = stringMatches[1];
		} else {
			std::cout << "no matches found" << std::endl;
		}
	}
    DbFile->close();
	delete DbFile;

	if (bookPath == "") return;
	DbFile= new std::ifstream();
	DbFile->open(bookPath);
	if (!DbFile->is_open()) {
		std::cout <<"file open error: " + bookPath << std::endl;
		exit(-1);
	}
	do {
		DbFile->getline(buffer,sizeof(buffer));
		if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
			std::string chapter = std::string(buffer);
			std::cout << "storing " << chapter << " into category array" << std::endl;
			chapters.push_back(Utilities::trim(chapter));
		} else {
			std::cout << "this read had a count of " << DbFile->gcount() << std::endl;
		}
	} while (!DbFile->eof());
	DbFile->close();
	delete DbFile;


}

BookInformation::~BookInformation() {
}

const std::string& BookInformation::getProjectName() const {
	return projectName;
}
const std::string& BookInformation::getProjectAcronym() const {
	return projectAcronym;
}

const std::vector<std::string>& BookInformation::getChapters() const {
	return chapters;
}

