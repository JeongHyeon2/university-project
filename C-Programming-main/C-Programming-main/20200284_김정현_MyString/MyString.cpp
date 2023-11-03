#include "MyString.h"
#include <cstddef>
#include<iostream>
#include<cmath>

using namespace std;

MyString::MyString()
{
	cArray = new char[FIRST_LENGTH_OF_ARRAY];
	size = FIRST_LENGTH_OF_ARRAY;
	dataLength = 0;
	cArray[0] = '\0';
}

MyString::MyString(const MyString& myString)
{
	copy(myString.cArray, myString.dataLength);
}

MyString::MyString(const char* srcStr)
{
	int cArrLength = findLength(srcStr);
	copy(srcStr, cArrLength);

}

void MyString::copy(const char* srcStr, int cArrLength) {
	dataLength = cArrLength;
	size = findAppropriateSize(dataLength + 1);
	cArray = new char[size];
	for (int i = 0; i < dataLength; i++) {
		cArray[i] = srcStr[i];
	}
	cArray[dataLength] = '\0';

}

MyString& MyString::operator=(const MyString& srcStr) 
{
	delete[] cArray;
	copy(srcStr.cArray, srcStr.dataLength);
	return *this;
}

MyString& MyString::operator=(const char* srcStr)  
{
	delete[] cArray;
	int cArrLength = findLength(srcStr);
	copy(srcStr, cArrLength);
	return *this;
}


MyString MyString::operator+(const char* str2nd) const 
{
	MyString mystring;
	mystring.append(cArray);
	mystring.append(str2nd);
	return mystring;
}

MyString MyString::operator+(const MyString& str2nd) const 
{
	MyString mystring;
	mystring.append(cArray);
	mystring.append(str2nd);
	return mystring;
}

MyString MyString::append(const char* str2nd)  
{
	int cArrLength = findLength(str2nd);
	append(str2nd, cArrLength);
	return *this;

}

MyString MyString::append(const MyString& str2nd) 
{
	append(str2nd.cArray, str2nd.dataLength);
	return *this;
}
void MyString::append(const char* str2nd, int cArrLength) {
	resize(findAppropriateSize(dataLength + cArrLength + 1));
	for (int i = dataLength, j = 0; i < size; i++, j++) {
		cArray[i] = str2nd[j];
	}
	dataLength = dataLength + cArrLength;
	cArray[dataLength] = '\0';
}

MyString MyString::insert(int pos, const char* subStr)
{
	if (pos > dataLength) return *this;
	int subStrLength = findLength(subStr);
	insert(pos, subStr, subStrLength);
	return *this;
}

MyString MyString::insert(int pos, const MyString& subStr)
{
	if (pos > dataLength) return *this;
	insert(pos, subStr.cArray, subStr.dataLength);
	return *this;
}
void MyString::insert(int pos, const char* subStr, int subStrLength) {
	size = findAppropriateSize(dataLength + subStrLength);
	char* result = new char[size];

	for (int i = 0; i < pos; i++) {
		result[i] = cArray[i];
	}
	for (int i = pos, j = 0; j < subStrLength; i++, j++) {
		result[i] = subStr[j];
	}
	for (int i = pos + subStrLength, j = pos; i < dataLength + subStrLength; i++, j++) {
		result[i] = cArray[j];
	}
	delete[] cArray;
	cArray = result;
	dataLength += subStrLength;
}


MyString MyString::replace(int pos, int cnt, const char* subStr)
{
	erase(pos, cnt);
	insert(pos, subStr);
	return *this;
}
MyString MyString::replace(int pos, int cnt, const MyString& subStr)
{
	erase(pos, cnt);
	insert(pos, subStr.cArray);
	return *this;
}
MyString MyString::erase(int pos, int cnt)
{
	if (pos < 0 || cnt<0 ) return *this;
	if (pos + cnt > dataLength) cnt = dataLength - pos;

	for (int i = pos; i < cnt; i++) {
		cArray[i] = '\0';
	}
	for (int i = pos; i < dataLength; i++) {
		cArray[i] = cArray[i + cnt];
	}
	dataLength -= cnt;
	return *this;
}

MyString MyString::substr(int pos, int cnt) const
{
	MyString result;
	if (cnt + pos > dataLength) cnt = dataLength - pos;
	result.size = findAppropriateSize(cnt);
	char* tmpArr = new char[result.size];
	for (int i = 0; i < cnt; i++, pos++) {
		tmpArr[i] = cArray[pos];
	}
	delete[] result.cArray;
	result.cArray = tmpArr;
	result.dataLength = cnt;

	return result;
}

MyString MyString::operator+=(const char* str2nd)
{
	return append(str2nd);
}

bool MyString::operator==(const MyString& str) const
{
	return isEqual(str.cArray, str.dataLength);
}

bool MyString::operator==(const char* str) const
{
	int strDataLength = findLength(str);
	return isEqual(str, strDataLength);
}
bool MyString::isEqual(const char* str, int strDataLength) const
{
	if (dataLength != strDataLength) return false;
	for (int i = 0; i < strDataLength; i++) {
		if (cArray[i] != str[i]) return false;
	}
	return true;
}

bool MyString::operator !=(const char* str) const
{
	return !(*this == str);
}

bool MyString::operator!=(const MyString& str) const
{
	return !(*this == str);
}


bool MyString::operator<(const char* cArr) const
{
	int cArrDataLength = findLength(cArr);
	return isRightBigger(cArr, cArrDataLength);
}

bool MyString::operator<=(const char* cArr) const
{
	return (*this == cArr) || (*this < cArr);
}
bool MyString::operator>(const char* cArr) const
{
	return !(*this <= cArr);
}
bool MyString::operator>=(const char* cArr) const
{
	return !(*this < cArr);
}

bool MyString::operator<(const MyString& str) const
{
	return isRightBigger(str.cArray, str.dataLength);
}
bool MyString::operator<=(const MyString& str) const
{
	return (*this == str) || (*this < str);
}
bool MyString::operator>(const MyString& str) const
{
	return !(*this <= str);
}
bool MyString::operator>=(const MyString& str) const
{
	return !(*this < str);
}

bool MyString::isRightBigger(const char* cArr, int cArrDataLength) const {
	int length = min(cArrDataLength, dataLength);
	for (int i = 0; i < length; i++) {
		if (cArray[i] < cArr[i]) return true;
		else if (cArray[i] > cArr[i]) return false;
	}
	if (dataLength < cArrDataLength) return true;
	return false;
}

int MyString::length() const
{
	return dataLength;
}

char MyString::at(int pos) const
{
	if (pos >= dataLength || pos<0) return '\0';
	return cArray[pos];
}


bool MyString::empty() const
{
	return dataLength == 0;
}


int MyString::find(const char* subStr, int pos) const
{
	int cDataLength = findLength(subStr);
	return findParticularStrPos(subStr, pos, cDataLength);
}
int MyString::find(const char* subStr) const
{
	int cDataLength = findLength(subStr);
	return findParticularStrPos(subStr, 0, cDataLength);
}

int MyString::find(const MyString subStr) const
{
	return findParticularStrPos(subStr.cArray, 0, subStr.dataLength);
}

int MyString::find(const MyString subStr, int pos) const
{
	return findParticularStrPos(subStr.cArray, pos, subStr.dataLength);
}


char MyString::operator [](int pos) const
{
	return at(pos);
}

void MyString::add(char c)
{
	cArray[dataLength++] = c;
	if (dataLength == size) resize(2 * size);
	else if (dataLength < size / 4) resize(size / 2);
	else cArray[dataLength] = '\0';
}

void MyString::resize(int newSize)
{
	char* newArr = new char[newSize];
	for (int i = 0; i < newSize; i++) {
		newArr[i] = cArray[i];
	}
	size = newSize;
	delete[] cArray;
	cArray = newArr;
	cArray[dataLength] = '\0';
}

int MyString::findLength(const char* arr) const
{
	int i = 0, length = 0;
	while (arr[i++] != '\0') {
		length++;
	}
	return length;
}

int stoi(const MyString& str, int pos, int base) //1~10진수
{
	if (pos<0 || pos>str.dataLength) return NULL;
	if (base == 16) {
		return stoi_16(str, pos, base);

	}
	else if (10 < base && base < 16) return 0;  //11~15진수 미구현

	int result = 0, count = 0;
	for (int i = pos; i < str.dataLength; i++) {
		if (0 <= str.cArray[i] - '0' && str.cArray[i] - '0' < base) count++;
		else break;
	}
	for (int i = pos, j = count - 1; i < count; i++, j--) {
		result += (int)(str.cArray[i] - '0') * (int)pow(base, j);

	}
	return result;
}

int stoi_16(const MyString& str, int pos, int base) //16진수
{
	int result = 0, count = 0;
	for (int i = pos; i < str.dataLength; i++) {
		if ('a' <= str.cArray[i] && str.cArray[i] <= 'f') str.cArray[i] = str.cArray[i] - ('a' - 'A');
	}
	for (int i = pos; i < str.dataLength; i++) {
		if (0 <= str.cArray[i] - '0' && str.cArray[i] - '0' < 10 ||
			'A' <= str.cArray[i] && str.cArray[i] <= 'F') count++;
		else break;
	}
	for (int i = pos, j = 0; j < count; i++, j++)
	{
		if (str.cArray[i] >= 'A' && str.cArray[i] <= 'F')
			result = result * 16 + str.cArray[i] - 'A' + 10;
		else
			result = result * 16 + str.cArray[i] - '0';
	}

	return result;
}

int MyString::findParticularStrPos(const char* arr, int pos, int cDataLength) const
{
	for (int i = pos, j = 0, count = 0; i < dataLength; i++) {
		if (cArray[i] == arr[j]) {
			j++;
			count++;
			if (cDataLength == j) return (i - cDataLength + 1);
		}
		else {
			count = 0;
			j = 0;
		}
	}
	return -1;
}

// num보다 크고 가장 가까운 2의 제곱수 반환
int MyString::findAppropriateSize(int num) const
{
	int i = 0;
	while (num >= pow(2, i)) {
		i++;
	}
	return (int)pow(2, i);
}
void MyString::clear() {
	delete[] cArray;
	cArray = new char[FIRST_LENGTH_OF_ARRAY];
	size = FIRST_LENGTH_OF_ARRAY;
	dataLength = 0;
	cArray[0] = '\0';
}

istream& operator>>(istream& is, MyString& str)
{
	str.clear();
	char c = '\0';
	while (c != '\n') {
		c = is.get();
		if (c == '\n') {
			str.cArray[str.dataLength] = '\0';
			break;
		}
		str.add(c);
	}
	return is;
}

ostream& operator<<(ostream& os, const MyString& str)
{
	for (int i = 0; i < str.dataLength; i++) {
		os << str.cArray[i];
	}
	return os;
}

MyString::~MyString()
{
	delete[] cArray;
}