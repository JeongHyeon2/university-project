#pragma once
#include<string>

using namespace std;

class Person {
public:
	Person(string name,string phonedNum,int donation);
	Person();
	~Person();
	string getName() const;
	void setName(string name);
	string getPhoneNum() const;
	void setPhoneNum(string phoneNum);
	int getDonation() const;
	void setDonation(int donation);
	virtual string print() const;
	virtual string getKey() const;
	virtual string toString() const;
private:
	string name;
	string phoneNum;
	int donation;
};