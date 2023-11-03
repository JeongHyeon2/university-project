#include"Staff.h"
#include<iostream>
using namespace std;

Staff::Staff(string name, string phonedNum, int donation, string sId,string d, string e):Person(name,phonedNum,donation)
{
	staffId = sId;
	department = d;
	extension = e;
}

Staff::Staff() : Person()
{
	department = "";
	extension = "";
}

Staff::~Staff()
{
}

void Staff::setStaffId(string sId)
{
	staffId = sId;
}

string Staff::getStaffId()
{
	return staffId;
}

string Staff::print() const
{
	string s;
	s.append("[교직원] ");
	s.append(this->getName());
	s.append("(사번:");
	s.append(staffId);
	s.append(", 부서:");
	s.append(department);
	s.append("(x");
	s.append(extension);
	s.append(")) ");
	s.append(this->getPhoneNum());
	s.append(" ");
	s.append(to_string(this->getDonation()));
	return s;
}

string Staff::getKey() const
{
	return staffId;
}

string Staff::toString() const
{
	string s;
	s.append("교직원 ");
	s.append(staffId);
	s.append(" ");
	s.append(this->getName());
	s.append(" ");
	s.append(department);
	s.append(" ");
	s.append(extension);
	s.append(" ");
	s.append(this->getPhoneNum());
	s.append(" ");
	s.append(to_string(this->getDonation()));
	return s;
}
