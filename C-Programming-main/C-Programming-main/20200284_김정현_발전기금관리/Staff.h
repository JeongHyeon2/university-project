#pragma once
#include<string>
#include"Person.h"

using namespace std;
class Staff : public Person
{
public:
	Staff(string name, string phonedNum, int donation, string staffId,string department,string extension);
	Staff();
	~Staff();
	void setStaffId(string staffId);
	string getStaffId();
	virtual string print() const;
	virtual string getKey() const;
	virtual string toString() const;
private:
	string staffId;
	string department;
	string extension;
};