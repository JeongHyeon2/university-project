#include"Student.h"
#include<iostream>
using namespace std;
Student::Student():Person()
{
	department = "";
}

Student::Student(string name, string phonedNum, int donation, string sId, string d):Person(name,phonedNum,donation)
{
	studentId = sId;
	department = d;
}

Student::~Student()
{
}

string Student::getDepartment() const
{
	return department;
}

void Student::setDepartment(string d)
{
	department = d;
}

string Student::getStudentId() const
{
	return studentId;
}

void Student::setStudentId(string sid)
{
	studentId = sid;
}

string Student::print() const
{
	string s;
	s.append("[ 학생 ] ");
	s.append(this->getName());
	s.append("(학번:");
	s.append(studentId);
	s.append(", 학과:");
	s.append(department);
	s.append(") ");
	s.append(this->getPhoneNum());
	s.append(" ");
	s.append(to_string(this->getDonation()));

	return s;
}

string Student::toString() const
{
	string s;
	s.append("학생 ");
	s.append(studentId);
	s.append(" ");
	s.append(this->getName());
	s.append(" ");
	s.append(department);
	s.append(" ");
	s.append(this->getPhoneNum());
	s.append(" ");
	s.append(to_string(this->getDonation()));

	return s;
}

string Student::getKey() const
{
	return studentId;
}
