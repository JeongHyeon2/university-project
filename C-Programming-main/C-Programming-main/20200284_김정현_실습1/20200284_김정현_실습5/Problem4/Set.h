#pragma once
#include<iostream>

using namespace std;
class Set {
public:
	Set();
	Set(const Set& set);
	~Set();
	void add(int num);
	void remove(int index);
	friend istream& operator>>(istream& inputStream, Set& s);
	friend ostream& operator<<(ostream& outputStream, const Set& s);
	Set operator|(Set s);
	Set operator&(Set s);
private:
	int* array;
	int size;
	int capacity;
	void add(int num,bool output); //For Union
	void resize(int capacity);
	void removeDuplication(int num, bool output);
};