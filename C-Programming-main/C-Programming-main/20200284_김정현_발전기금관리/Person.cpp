#include "Person.h"
#include<iostream>
using namespace std;

Person::Person(string n, string phNum, int d)
{
	name = n;
	phoneNum = phNum;
	donation=d;
}

Person::Person()
{
	name = "";
	phoneNum = "";
	donation = 0;
}

Person::~Person()
{

}

string Person::getName() const
{
	return name;
}

void Person::setName(string n)
{
	name = n;
}

string Person::getPhoneNum() const
{
	return phoneNum;
}

void Person::setPhoneNum(string phNum)
{
	phoneNum = phNum;
}

int Person::getDonation() const
{
	return donation;
}

void Person::setDonation(int d)
{
	donation = d;
}

string Person::print() const
{
	return "Person";
}

string Person::getKey() const
{
	return string();
}

string Person::toString() const
{
	return string();
}
