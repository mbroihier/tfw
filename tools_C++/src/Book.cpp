/*
 * Book.cpp
 *
 *  Created on: Apr 15, 2017
 *      Author: broihier
 */

#include "Book.h"
#include "BookInformation.h"
#include "TestDatabase.h"
#include "Chapter.h"
#include "DOMizeTemplate.h"
#include <sys/stat.h>
#include <iostream>
#include <stdio.h>


Book::Book(std::string bookPath) {
	BookInformation * bookInformation = new BookInformation(bookPath);
	std::vector<std::string> categories = bookInformation->getChapters();
	std::string projectName = bookInformation->getProjectName();
	DOMizeTemplate * DOMizedTemplate = new DOMizeTemplate("../tools_configuration/BOOK_TOC_TEMPLATE.html","./"+projectName+"_toc.html");
	const xml_document& doc = DOMizedTemplate->getDocument();

	xpath_node sectionTitle = doc.select_node("//h1[@id='bookTitle']");
	if (sectionTitle) {
		sectionTitle.node().append_child(node_pcdata).set_value((bookInformation->getProjectName()+" - System Level Test Procedures").c_str());
	} else {
		std::cout << "bookTitle not found" << std::endl;
	}

	xpath_node list = doc.select_node("//ol[@id='sectionList']");
	xml_node firstChildInList = list.node().first_child();
	if (list) {
		for (std::string chapterName : categories){
			std::cout << "working on " << chapterName << " of project " << bookInformation->getProjectAcronym() << std::endl;
			mkdir(chapterName.c_str(),0777);
			TestDatabase database(chapterName,bookInformation->getProjectAcronym());
			Chapter * chapter = new Chapter(chapterName,database);
			std::string status = chapter->getStatus();
			std::string title = chapter->getTitle();
			//xml_node listItem = list.node().append_child("li");
			xml_node listItem = list.node().insert_child_before("li",firstChildInList);
			xml_node anchor = listItem.append_child("a");
			anchor.append_attribute("href") = (chapterName+"_toc.html").c_str();
			if (status == "PASSED") {
				anchor.append_attribute("style") = "color: 00F000";
			} else if (status == "FAILED") {
				anchor.append_attribute("style") = "color: FF0000";
			}
			anchor.append_child(node_pcdata).set_value((chapterName + " - " + title).c_str());
			delete chapter;
		}
	} else {
		std::cout << "list not found" << std::endl;
	}

	DOMizedTemplate->writeDocument();
	delete bookInformation;
	delete DOMizedTemplate;
}


Book::~Book() {
}

