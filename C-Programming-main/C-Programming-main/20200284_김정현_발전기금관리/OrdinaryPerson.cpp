#include"OrdinaryPerson.h"
#include<iostream>
using namespace std;
OrdinaryPerson::OrdinaryPerson() :Person()
{
	keyCode = "";
}
OrdinaryPerson::OrdinaryPerson(string name, string phoneNum, int donation,string code):Person(name,phoneNum,donation)
{
	keyCode = code;
}
OrdinaryPerson::~OrdinaryPerson()
{
}

string OrdinaryPerson::print() const
{

	string s;
	s.append("[ 일반 ] ");
	s.append(this->getName());
	s.append("(기탁자코드:");
	s.append(keyCode);
	s.append(") ");
	s.append(this->getPhoneNum());
	s.append(" ");
	s.append(to_string(this->getDonation()));
	return s;

}

string OrdinaryPerson::getKey() const
{
	return keyCode;
}

string OrdinaryPerson::toString() const
{
	string s;
	s.append("일반 ");
	s.append(this->getKey());
	s.append(" ");
	s.append(this->getName());
	s.append(" ");
	s.append(this->getPhoneNum());
	s.append(" ");
	s.append(to_string(this->getDonation()));
	return s;
}
