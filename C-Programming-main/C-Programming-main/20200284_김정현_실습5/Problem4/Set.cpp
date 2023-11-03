#include "Set.h"

Set::Set() {
	capacity = 4;
	size = 0;
	array = new int[capacity];
}
Set::Set(const Set& set)
{
	capacity = set.capacity;
	size = set.size;
	array = new int[capacity];
	for (int i = 0; i < size;  i++) {
		array[i] = set.array[i];
	}
}
Set::~Set()
{
	delete[] array;
}
void Set::resize(int newCapacity) {
	int* newArr = new int[newCapacity];

	for (int i = 0; i < newCapacity; i++) {
		newArr[i] = array[i];
	}
	capacity = newCapacity;
	delete[] array;
	array = newArr;
}

void Set::removeDuplication(int num,bool output)
{
	if (size ==1) return;
	for (int i = size - 2; i >= 0; i--) {
		if (num == array[i]) {
			if(output) cout << array[i] << "는 중복 원소이므로 제거" << endl;
			remove(i);
		}
	}
}

void Set::add(int num)
{
	add(num, true);
}
void Set::add(int num,bool output)
{
	if (size == capacity) resize(2 * capacity);
	array[size++] = num;
	removeDuplication(num,output);
}

void Set::remove(int index)
{
	if ((size - 1 < index)|| index < 0) return;
	if (size < capacity / 4) resize(capacity / 2);

	array[index] = array[size-1];
	array[size-1] = NULL;
	size--;
}

Set Set::operator|(Set s)
{
	Set result;
	for (int i = 0; i < size; i++) result.add(array[i],false);
	for (int i = 0; i < s.size; i++) result.add(s.array[i],false);

	return result;
}

Set Set::operator&(Set s)
{
	Set result;
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < s.size; j++) {
			if (array[i] == s.array[j]) {
				result.add(array[i]);
			}
		}
	}
	
	return result;
}

istream& operator>>(istream& inputStream, Set& s)
{
	int num=0;
	while (num>=0)
	{
		cin >> num;
		if (num >= 0) {
			s.add(num);
		}

	}
	return inputStream;
}

ostream& operator<<(ostream& outputStream, const Set& s)
{
	for (int i = 0; i < s.size; i++) {
		cout << s.array[i] << " ";
	}
	return outputStream;
}
