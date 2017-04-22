/*
 * DOMizeTemplate.h
 *
 *  Created on: Apr 10, 2017
 *      Author: broihier
 */

#ifndef DOMIZETEMPLATE_H_
#define DOMIZETEMPLATE_H_

#include "pugixml.hpp"
#include <fstream>


class DOMizeTemplate {

private:
	pugi::xml_document doc;
	pugi::xml_parse_result DOMizedDocument;
	std::ofstream *HTMLFile;

public:
	DOMizeTemplate(const std::string _template, const std::string filePath);
	virtual ~DOMizeTemplate();
	const pugi::xml_document& getDocument(void) const;
	void writeDocument(void);

};

#endif /* DOMIZETEMPLATE_H_ */
