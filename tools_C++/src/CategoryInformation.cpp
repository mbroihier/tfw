/*
 * CategoryInformation.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "CategoryInformation.h"
#include <stdio.h>
#include <iostream>
#include <fstream>

CategoryInformation::CategoryInformation(std::string category) {
	std::ifstream * DbFile = new std::ifstream();
	DbFile->open(category + ".testDbCategoryTitle");
	if (!DbFile->is_open()) {
		std::cout << "file open error: " + category + ".testDbCategoryTitle"
				<< std::endl;
		exit(-1);
	}
	char buffer[32767];
	DbFile->getline(buffer, sizeof(buffer));
	if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
		categoryTitle = std::string(buffer);
	}
	DbFile->close();

	DbFile->open(category + ".testDbCategoryDescription");
	if (!DbFile->is_open()) {
		std::cout << "file open error: " + category + ".testDbCategoryDescription"
				<< std::endl;
		exit(-1);
	}
	DbFile->getline(buffer, sizeof(buffer));
	if (DbFile->gcount() > 0 && DbFile->gcount() < sizeof(buffer)) {
		categoryDescriptionText = std::string(buffer);
	}
	DbFile->close();
	delete DbFile;
}

CategoryInformation::~CategoryInformation() {
}

std::string CategoryInformation::getCategoryTitle(){
	return categoryTitle;
}

std::string CategoryInformation::getCategoryDescriptionText(){
	return categoryDescriptionText;
}
