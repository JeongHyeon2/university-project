#include<iostream>
#include<string>


using namespace std;
int main() {
	const int NUMBER_OF_ALPHABET = 26;
	int count[NUMBER_OF_ALPHABET] = {};
	cout << "���� �Է�: ";
	string str;
	getline(cin, str);

	//�ҹ��ڷ� ����
	for (int i = 0; i < str.length(); i++)
		str[i] = tolower(str[i]);

	// ���ĺ� ī��Ʈ
	for (int i = 0; i < str.length(); i++) {
		if ('a' <= str[i] && str[i] <= 'z') {
			count[str[i] - 'a']++;
		}
	}

	//���
	for (int i = 0; i < NUMBER_OF_ALPHABET; i++)
		if (count[i] != 0) {
			cout << "[" << (char)(i + 'a') << "]" << count[i] << " ";
		}
	return 0;

}