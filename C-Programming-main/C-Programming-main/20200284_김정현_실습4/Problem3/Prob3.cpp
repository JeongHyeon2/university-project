#include<iostream>
#include<string>


using namespace std;
int main() {
	const int NUMBER_OF_ALPHABET = 26;
	const int NUMBER_OF_ENCRYPTION = 3;
	cout << "���� �Է�: ";
	string str;
	getline(cin, str);

	for (int i = 0; i < str.length(); i++) {
		if ('a' <= str[i] && str[i] <= 'z') //�ҹ����϶�
			str[i] = 'a' + ((str[i] - 'a' + NUMBER_OF_ENCRYPTION) % NUMBER_OF_ALPHABET);
		else if ('A' <= str[i] && str[i] <= 'Z') //�빮���϶�
			str[i] = 'A' + ((str[i] - 'A' + NUMBER_OF_ENCRYPTION) % NUMBER_OF_ALPHABET);
	}
	cout << str;
	return 0;

}