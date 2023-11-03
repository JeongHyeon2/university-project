#ifndef MYLIST_H
#define MYLIST_H
#pragma once
#include<iostream>
#include<fstream>

#include "LinkedList.hpp"
#include"Person.h"
#include"Student.h"
#include"Staff.h"
#include"OrdinaryPerson.h"

using namespace std;
template<typename T>
class MyList {
public:
	MyList();
	~MyList();
	void run(const string &path);
private:
	LinkedList<T>* list;

	string ordinaryPersonCode;
	const int ORDINARY_PERSON_CODE_LENGTH = 6;
	const string ORDINARY_PERSON_CODE = "V";

	void readAll() const;
	void registerPerson();
	void updatePerson();
	void deletePerson();
	void exitProgram(const string &path) const;
	void saveFile(const string &path) const;
	void loadFile(const string &path);

	void add(T* data); 

	bool priorityDecision(const Person* t1, const Person* t2) const;
	bool checkPhoneNumDuplicate(const string &phNum) const;
	bool checkStudentIdDuplicate(const string &stdudentId) const;
	bool checkStaffIdDuplicate(const string &staffId) const;

	void setOrdinaryPersonCode();
	void setOrdinaryPersonCode(const string &key);
};
#endif
