#pragma once
#include <iostream>
using namespace std;
class MyString {
public:
	MyString();
	MyString(const MyString& myString);
	MyString(const char* srcStr);
	~MyString();

	MyString& operator = (const MyString& srcStr);
	MyString& operator = (const char* srcStr);

	MyString operator + (const char* str2nd) const;
	MyString operator + (const MyString& str2nd) const;
	MyString append(const char* str2nd);
	MyString append(const MyString& str2nd);
	MyString operator += (const char* str2nd);

	MyString substr(int pos, int cnt) const;

	char operator [](int pos) const;

	MyString insert(int pos, const char* subStr);
	MyString insert(int pos, const MyString& subStr);

	MyString replace(int pos, int cnt, const char* subStr);
	MyString replace(int pos, int cnt, const MyString& subStr);

	MyString erase(int pos, int cnt);

	bool operator ==(const MyString& str) const;
	bool operator ==(const char* str) const;
	bool operator !=(const char* str) const;
	bool operator !=(const MyString& str) const;
	bool operator < (const MyString& str) const;
	bool operator <= (const MyString& str) const;	
	bool operator > (const MyString& str) const;
	bool operator >= (const MyString& str) const;
	bool operator < (const char* cArr) const;
	bool operator <= (const char* cArr) const;
	bool operator > (const char* cArr) const;
	bool operator >= (const char* cArr) const;

	int find(const char* subStr) const;
	int find(const MyString subStr) const;
	int find(const char* subStr, int pos) const;
	int find(const MyString subStr, int pos) const;

	int length() const;
	char at(int pos) const;
	bool empty() const;

	friend  int stoi(const MyString& str, int pos, int base);
	friend  int stoi_16(const MyString& str, int pos, int base);
	friend istream& operator >>(istream& is, MyString& str);
	friend ostream& operator <<(ostream& os, const MyString& str);
private:
	char *cArray;
	int size;
	int dataLength;
	const int FIRST_LENGTH_OF_ARRAY = 8;

	void clear();

	void add(char c);
	void resize(int dataLength);

	int findLength(const char* arr) const;
	int findParticularStrPos(const char* arr, int pos, int cDataLength) const;
	int findAppropriateSize(int num) const;

	void copy(const char* srcStr,int cArrLength);
	void append(const char* str2nd, int cArrLength);
	void insert(int pos, const char* subStr, int subStrLength);

	bool isRightBigger(const char* cArr,int cArrDataLength) const;
	bool isEqual(const char* str, int strDataLength) const;
};