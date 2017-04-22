/*
 * Chapter.h
 *
 *  Created on: Apr 15, 2017
 *      Author: broihier
 */

#ifndef CHAPTER_H_
#define CHAPTER_H_

#include "pugixml.hpp"
#include <string>
#include "TestDatabase.h"

using namespace pugi;

class Chapter {
private:
	std::string status;
	std::string title;

	void updateChapterStatus(std::string status);

public:
	Chapter(std::string category, TestDatabase& database);
	virtual ~Chapter();
	const std::string& getTitle() const;
	const std::string& getStatus() const;
};

#endif /* CHAPTER_H_ */
