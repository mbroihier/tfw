/*
 * DOMizeTemplate.cpp
 *
 *  Created on: Apr 10, 2017
 *      Author: broihier
 */

#include "DOMizeTemplate.h"
#include <stdio.h>
#include <iostream>
#include <fstream>


DOMizeTemplate::DOMizeTemplate(const std::string _template, const std::string filePath) {
	HTMLFile = new std::ofstream();
	HTMLFile->open(filePath);
	if (!HTMLFile->is_open()) {
		std::cout <<"file open error: " + filePath << std::endl;
	}
	DOMizedDocument = doc.load_file(_template.c_str());
	if (!DOMizedDocument) {
		std::cout << "Template failed to open: " << _template << std::endl;
	}
}


DOMizeTemplate::~DOMizeTemplate() {
	if (HTMLFile) {
		HTMLFile->close();
		delete (HTMLFile);
	}
}

const pugi::xml_document& DOMizeTemplate::getDocument(void) const {
	return doc;
}

void DOMizeTemplate::writeDocument(void) {
	doc.save(*HTMLFile);
}
