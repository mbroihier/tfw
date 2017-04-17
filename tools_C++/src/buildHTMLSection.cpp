/*
 * buildHTMLSection.cpp
 *
 *  Created on: Apr 17, 2017
 *      Author: broihier
 */
#include <iostream>
#include <stdio.h>
#include <sys/stat.h>


#include "Chapter.h"
#include "BookInformation.h"

int main(const int argc, const char * argv[]) {

	std::string chapterName = std::string(argv[1]);
	std::cout << "processing " << argv[1] << std::endl;
	BookInformation * bookInformation = new BookInformation("");
	mkdir(chapterName.c_str(), 0777);
	TestDatabase database(chapterName, bookInformation->getProjectAcronym());
	Chapter * chapter = new Chapter(chapterName, database);
	delete chapter;
	delete bookInformation;

	return 0;

}

