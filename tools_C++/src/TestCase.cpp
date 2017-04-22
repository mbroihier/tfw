/*
 * TestCase.cpp
 *
 *  Created on: Apr 13, 2017
 *      Author: broihier
 */

#include "TestCase.h"
#include <iostream>
#include <stdio.h>


TestCase::TestCase(std::string id, std::string category, TestDatabase& database) {
	std::cout << "inside of test case constructor" << std::endl;
	DOMizedTemplate = new DOMizeTemplate("../tools_configuration/TEST_CASE_TEMPLATE.html","./"+category+"/"+id+".html");
	const xml_document& doc = DOMizedTemplate->getDocument();
	status = "UNTESTED";
	xpath_node field = doc.select_node("//td[@id='testCaseId']");
	if (field) {
	    field.node().append_child(node_pcdata).set_value(id.c_str());
	} else {
		std::cout << "testCaseId not found" << std::endl;
	}

	field = doc.select_node("//td[@id='title']");
	if (field) {
	    field.node().append_child(node_pcdata).set_value(database.getIdentificationText(id).c_str());
	} else {
		std::cout << "title not found" << std::endl;
	}

	field = doc.select_node("//ul[@id='requirementsList']");
	if (field) {
		for (std::string requirementId : Utilities::split(database.getRequirementsInThisTest(id),",")) {
			std::string trimmedRequirementId = Utilities::trim(requirementId);
			xml_node listItem = field.node().append_child("li");
			listItem.append_child(node_pcdata).set_value(database.getRequirementsText(trimmedRequirementId).c_str());
		}
	} else {
		std::cout << "requirementsList not found" << std::endl;
	}

	field = doc.select_node("//td[@id='objective']");
	if (field) {
		xml_node paragraphItem = field.node().insert_child_before("p",field.node().first_child());
		paragraphItem.append_child(node_pcdata).set_value("This test case is intended to verify the following implied or specified requirements:");
		paragraphItem = field.node().append_child("p");
		paragraphItem.append_child(node_pcdata).set_value(database.getObjectiveText(id).c_str());
	} else {
		std::cout << "objective not found" << std::endl;
	}

	field = doc.select_node("//ul[@id='setup']");
	if (field) {
		for (std::string setupItem : Utilities::split(database.getSetupText(id),"/hr")) {
			xml_node listItem = field.node().append_child("li");
			listItem.append_child(node_pcdata).set_value(setupItem.c_str());
		}
	} else {
		std::cout << "setup not found" << std::endl;
	}

	field = doc.select_node("//ol[@id='actions']");
	if (field) {
		for (std::string setupItem : Utilities::split(database.getProcedureText(id),"/step")) {
			if (setupItem == "") continue;
			bool procedureStep = true;
			bool firstBullet = true;
			xml_node unOrderedList;
			for (std::string item : Utilities::split(setupItem,"/bullet")) {
				if (procedureStep) {
			        xml_node listItem = field.node().append_child("li");
			        if (item.find("\\step") != std::string::npos) {
			            item.replace(item.find("\\step"),item.find("\\step")+strlen("\\step"),"");
			        }
			        listItem.append_child(node_pcdata).set_value(item.c_str());
			        procedureStep = false;
				} else {
					if (firstBullet) {
					    unOrderedList = field.node().append_child("ul");
					    firstBullet = false;
					}
					if (item.find("\\step") != std::string::npos) {
			            item.replace(item.find("\\step"),item.find("\\step")+strlen("\\step"),"");
					}
					if (item.find("\\bullet") != std::string::npos) {
			            item.replace(item.find("\\bullet"),item.find("\\bullet")+strlen("\\bullet"),"");
					}
			        unOrderedList.append_child("li");
					unOrderedList.append_child(node_pcdata).set_value(item.c_str());
				}
			}
		}
	} else {
		std::cout << "actions not found" << std::endl;
	}

	field = doc.select_node("//td[@id='expected']");
	if (field) {
	    field.node().append_child(node_pcdata).set_value(database.getExpectedResultsText(id).c_str());
	} else {
		std::cout << "expected not found" << std::endl;
	}

	field = doc.select_node("//td[@id='results']");
	if (field) {
		status = database.getResultsText(id);
	    field.node().append_child(node_pcdata).set_value(database.getResultsText(id).c_str());
	} else {
		std::cout << "results not found" << std::endl;
	}

	field = doc.select_node("//ul[@id='cleanup']");
	if (field) {
		for (std::string cleanupItem : Utilities::split(database.getCleanupText(id),"/hr")) {
			xml_node listItem = field.node().append_child("li");
			listItem.append_child(node_pcdata).set_value(cleanupItem.c_str());
		}
	} else {
		std::cout << "cleanup not found" << std::endl;
	}

	field = doc.select_node("//td[@id='requirements']");
	if (field) {
	    field.node().append_child(node_pcdata).set_value(database.getRequirementsInThisTest(id).c_str());
	} else {
		std::cout << "requirements not found" << std::endl;
	}

	DOMizedTemplate->writeDocument();
	std::cout << "test case constructor completed" << std::endl;

	delete DOMizedTemplate;
}

TestCase::~TestCase() {
	std::cout << "test case destructor starting" << std::endl;
	if (DOMizedTemplate) delete DOMizedTemplate;
}

const std::string& TestCase::getStatus(void) const{
	return status;
}
