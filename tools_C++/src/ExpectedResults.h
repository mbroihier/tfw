/*
 * ExpectedResults.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef EXPECTEDRESULTS_H_
#define EXPECTEDRESULTS_H_

#include "DatabaseComponent.h"
#include <string>

class ExpectedResults: public DatabaseComponent {
public:
	ExpectedResults(std::string category);
	virtual ~ExpectedResults();
	const std::string& getExpectedResultsText(const std::string& id) const;
};

#endif /* EXPECTEDRESULTS_H_ */
