/*
 * Book.h
 *
 *  Created on: Apr 15, 2017
 *      Author: broihier
 */

#ifndef BOOK_H_
#define BOOK_H_

#include <string>

class Book {
public:
	Book(std::string bookPath);
	virtual ~Book();
};

#endif /* BOOK_H_ */
