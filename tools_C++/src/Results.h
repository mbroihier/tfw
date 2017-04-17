/*
 * Results.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef RESULTS_H_
#define RESULTS_H_

#include "DatabaseComponent.h"
#include <string>

class Results: public DatabaseComponent {
public:
	Results(std::string category);
	virtual ~Results();
	std::string getResultsText(std::string id);
};

#endif /* RESULTS_H_ */
