/*
 * TestDatabase.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef TESTDATABASE_H_
#define TESTDATABASE_H_

#include <vector>
#include <string>
#include "Identification.h"
#include "Objective.h"
#include "Setup.h"
#include "Procedures.h"
#include "ExpectedResults.h"
#include "Results.h"
#include "Cleanup.h"
#include "CategoryInformation.h"
#include "Requirements.h"

class TestDatabase {
private:
	Identification      * identification;
	Objective           * objective;
	Setup               * setup;
	Procedures          * procedures;
	ExpectedResults     * expectedResults;
	Results             * results;
	Cleanup             * cleanup;
	CategoryInformation * categoryInformation;
	Requirements        * requirements;
public:
	TestDatabase(std::string category, std::string project);
	~TestDatabase();
	std::vector<std::string> getIdentificationIds();
	std::string getIdentificationText(std::string id);
	std::string getObjectiveText(std::string id);
	std::string getSetupText(std::string id);
	std::string getProcedureText(std::string id);
	std::string getExpectedResultsText(std::string id);
	std::string getResultsText(std::string id);
	std::string getCleanupText(std::string id);
	std::string getCategoryTitle();
	std::string getCategoryDescriptionText();
	std::string getRequirementsText(std::string id);
	std::string getRequirementsInThisTest(std::string id);
};

#endif /* TESTDATABASE_H_ */
