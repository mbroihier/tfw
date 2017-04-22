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
	const std::vector<std::string>& getIdentificationIds() const;
	const std::string& getIdentificationText(const std::string& id) const;
	const std::string& getObjectiveText(const std::string& id) const;
	const std::string& getSetupText(const std::string& id) const;
	const std::string& getProcedureText(const std::string& id) const;
	const std::string& getExpectedResultsText(const std::string& id) const;
	const std::string& getResultsText(const std::string& id) const;
	const std::string& getCleanupText(const std::string& id) const;
	const std::string& getCategoryTitle() const;
	const std::string& getCategoryDescriptionText() const;
	const std::string& getRequirementsText(const std::string& id) const;
	const std::string& getRequirementsInThisTest(const std::string& id) const;
};

#endif /* TESTDATABASE_H_ */
