#include<iostream>
#include<string>


using namespace std;
int main() {
	const int NUMBER_OF_ALPHABET = 26;
	int count[NUMBER_OF_ALPHABET] = {};
	cout << "문장 입력: ";
	string str;
	getline(cin, str);

	//소문자로 변경
	for (int i = 0; i < str.length(); i++)
		str[i] = tolower(str[i]);

	// 알파벳 카운트
	for (int i = 0; i < str.length(); i++) {
		if ('a' <= str[i] && str[i] <= 'z') {
			count[str[i] - 'a']++;
		}
	}

	//출력
	for (int i = 0; i < NUMBER_OF_ALPHABET; i++)
		if (count[i] != 0) {
			cout << "[" << (char)(i + 'a') << "]" << count[i] << " ";
		}
	return 0;

}