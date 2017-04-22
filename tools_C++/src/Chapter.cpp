/*
 * Chapter.cpp
 *
 *  Created on: Apr 15, 2017
 *      Author: broihier
 */

#include "Chapter.h"
#include "TestDatabase.h"
#include "DOMizeTemplate.h"
#include "TestCase.h"
#include <iostream>
#include <stdio.h>


Chapter::Chapter(std::string category, TestDatabase& database) {
	DOMizeTemplate * DOMizedTemplate = new DOMizeTemplate("../tools_configuration/TOC_Template.html","./"+category+"_toc.html");
	const xml_document& doc = DOMizedTemplate->getDocument();
	this->status = "UNTESTED";

	xpath_node sectionTitle = doc.select_node("//h1[@id='title']");
	if (sectionTitle) {
		this->title = database.getCategoryTitle();
		sectionTitle.node().append_child(node_pcdata).set_value(database.getCategoryTitle().c_str());
	} else {
		std::cout << "section title not found" << std::endl;
	}

	xpath_node sectionDescription = doc.select_node("//p[@id='description']");
	if (sectionDescription) {
		sectionDescription.node().append_child(node_pcdata).set_value(database.getCategoryDescriptionText().c_str());
	} else {
		std::cout << "section description not found" << std::endl;
	}

	xpath_node list = doc.select_node("//ol[@id='list']");
	xml_node firstChildInList = list.node().first_child();
	if (list) {
		for (std::string caseId : database.getIdentificationIds()){
			std::cout << "working on test case: " << caseId << std::endl;
			std::string status = (new TestCase(caseId,category,database))->getStatus();
			xml_node listItem = list.node().append_child("li");
			xml_node anchor = listItem.append_child("a");
			anchor.append_attribute("href") = (category+"/"+caseId+".html").c_str();
			if (status == "PASSED") {
				anchor.append_attribute("style") = "color: 00F000";
			} else if (status == "FAILED") {
				anchor.append_attribute("style") = "color: FF0000";
			}
			anchor.append_child(node_pcdata).set_value((caseId + " - " + database.getIdentificationText(caseId)).c_str());
			updateChapterStatus(status);
		}
	} else {
		std::cout << "list not found" << std::endl;
	}
	DOMizedTemplate->writeDocument();
	delete DOMizedTemplate;

}

Chapter::~Chapter() {
}

const std::string& Chapter::getTitle() const{
	return title;
}

const std::string& Chapter::getStatus() const{
	return status;
}

void Chapter::updateChapterStatus(std::string status) {
	if (status == "UNTESTED") {
		if (this->status != "UNTESTED") {
			this->status = "NOTDONE";
		}
	} else {
		if (status == "FAILED") {
			this->status = "FAILED";
		} else {
			if (this->status == "UNTESTED") {
				this->status = "PASSED";
			}
		}
	}
}
