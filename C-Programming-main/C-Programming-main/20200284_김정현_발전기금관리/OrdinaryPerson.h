#pragma once
#include"Person.h"
#include<string>
using namespace std;
class OrdinaryPerson:public Person
{
public:
	OrdinaryPerson();
	OrdinaryPerson(string name, string phoneNum, int donation, string keyCode);
	~OrdinaryPerson();
	virtual string print() const;
	virtual string getKey() const;
	virtual string toString() const;

private:
	string keyCode;
};