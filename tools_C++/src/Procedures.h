/*
 * Procedures.h
 *
 *  Created on: Apr 12, 2017
 *      Author: broihier
 */

#ifndef PROCEDURES_H_
#define PROCEDURES_H_

#include "DatabaseComponent.h"
#include <string>

class Procedures: public DatabaseComponent {
public:
	Procedures(std::string category);
	virtual ~Procedures();
	const std::string& getProcedureText(const std::string& id) const;
};

#endif /* PROCEDURES_H_ */
