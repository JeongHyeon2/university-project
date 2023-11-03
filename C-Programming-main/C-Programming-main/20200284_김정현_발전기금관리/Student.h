#pragma once
#include"Person.h"
#include<string>
using namespace std;
class Student : public Person
{
public:
	Student();
	Student(string name, string phonedNum, int donation,string studentId,string department);
	~Student();
	string getDepartment() const;
	void setDepartment(string department);
	string getStudentId() const;
	void setStudentId(string studentId);
	virtual string print() const;
	virtual string toString() const;
	virtual string getKey() const;
private:
	string department;
	string studentId;
};