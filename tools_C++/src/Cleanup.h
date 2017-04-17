/*
 * Cleanup.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef CLEANUP_H_
#define CLEANUP_H_

#include "DatabaseComponent.h"
#include <string>

class Cleanup: public DatabaseComponent {
public:
	Cleanup(std::string category);
	virtual ~Cleanup();
	std::string getCleanupText(std::string id);
};

#endif /* CLEANUP_H_ */
