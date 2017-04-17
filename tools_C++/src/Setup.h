/*
 * Setup.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef SETUP_H_
#define SETUP_H_

#include "DatabaseComponent.h"
#include <string>

class Setup: public DatabaseComponent {
public:
	Setup(std::string category);
	virtual ~Setup();
	std::string getSetupText(std::string id);
};

#endif /* SETUP_H_ */
