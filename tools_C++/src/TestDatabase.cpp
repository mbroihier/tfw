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

const std::vector<std::string>& TestDatabase::getIdentificationIds() const{
	return identification->getIdentificationIds();
}

const std::string& TestDatabase::getIdentificationText(const std::string& id) const{
	return identification->getIdentificationText(id);
}

const std::string& TestDatabase::getObjectiveText(const std::string& id) const{
	return objective->getObjectiveText(id);
}
const std::string& TestDatabase::getRequirementsInThisTest(const std::string& id) const{
	return objective->getRequirementsInThisTest(id);
}

const std::string& TestDatabase::getSetupText(const std::string& id) const{
	return setup->getSetupText(id);
}

const std::string& TestDatabase::getProcedureText(const std::string& id) const{
	return procedures->getProcedureText(id);
}

const std::string& TestDatabase::getExpectedResultsText(const std::string& id) const{
	return expectedResults->getExpectedResultsText(id);
}

const std::string& TestDatabase::getResultsText(const std::string& id) const{
	return results->getResultsText(id);
}

const std::string& TestDatabase::getCleanupText(const std::string& id) const{
	return cleanup->getCleanupText(id);
}

const std::string& TestDatabase::getCategoryTitle() const{
	return categoryInformation->getCategoryTitle();
}

const std::string& TestDatabase::getCategoryDescriptionText() const{
	return categoryInformation->getCategoryDescriptionText();
}
const std::string& TestDatabase::getRequirementsText(const std::string& id) const{
	return requirements->getRequirementsText(id);
}
