/*
 * TestDatabase.cpp
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#include "TestDatabase.h"
#include <iostream>
#include <stdio.h>
#include <execinfo.h>
#include <unistd.h>
#include <stdlib.h>



TestDatabase::TestDatabase(std::string category, std::string project) {
	std::cout << "building test database with category: " << category << " for project " << project << std::endl;
	identification = new Identification(category);
	objective = new Objective(category);
	setup = new Setup(category);
	procedures = new Procedures(category);
	expectedResults = new ExpectedResults(category);
	results = new Results(category);
	cleanup = new Cleanup(category);
	categoryInformation = new CategoryInformation(category);
	std::vector<std::string> requirementsList;
	requirementsList.push_back(project);
	requirements = new Requirements(requirementsList);

}

TestDatabase::~TestDatabase() {

	if (identification) delete identification;
	if (objective) delete objective;
	if (setup) delete setup;
	if (procedures) delete procedures;
	if (expectedResults) delete expectedResults;
	if (results) delete results;
	if (cleanup) delete cleanup;
	if (categoryInformation) delete categoryInformation;
	if (requirements) delete requirements;
}

std::vector<std::string> TestDatabase::getIdentificationIds() {
	return identification->getIdentificationIds();
}

std::string TestDatabase::getIdentificationText(std::string id) {
	return identification->getIdentificationText(id);
}

std::string TestDatabase::getObjectiveText(std::string id) {
	return objective->getObjectiveText(id);
}
std::string TestDatabase::getRequirementsInThisTest(std::string id){
	return objective->getRequirementsInThisTest(id);
}

std::string TestDatabase::getSetupText(std::string id) {
	return setup->getSetupText(id);
}

std::string TestDatabase::getProcedureText(std::string id) {
	return procedures->getProcedureText(id);
}

std::string TestDatabase::getExpectedResultsText(std::string id) {
	return expectedResults->getExpectedResultsText(id);
}

std::string TestDatabase::getResultsText(std::string id) {
	return results->getResultsText(id);
}

std::string TestDatabase::getCleanupText(std::string id) {
	return cleanup->getCleanupText(id);
}

std::string TestDatabase::getCategoryTitle() {
	return categoryInformation->getCategoryTitle();
}

std::string TestDatabase::getCategoryDescriptionText() {
	return categoryInformation->getCategoryDescriptionText();
}
std::string TestDatabase::getRequirementsText(std::string id){
	return requirements->getRequirementsText(id);
}
