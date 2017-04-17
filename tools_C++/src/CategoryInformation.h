/*
 * CategoryInformation.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef CATEGORYINFORMATION_H_
#define CATEGORYINFORMATION_H_

#include <string>

class CategoryInformation {
private:
	std::string categoryDescriptionText;
	std::string categoryTitle;
public:
	CategoryInformation(std::string category);
	virtual ~CategoryInformation();
	std::string getCategoryTitle();
	std::string getCategoryDescriptionText();
};

#endif /* CATEGORYINFORMATION_H_ */
