#include<iostream>
#include<string>


using namespace std;
int main() {
	cout << "���� �Է�:";
	string str;

	getline(cin, str);

	// Ltrim
	int idx = 0;
	while (str[idx]==' ' || str[idx] == '\t') {
		idx++;
	}
	str.erase(0, idx);
	
	if ('a' <= str[0] && str[0] <= 'z') {
		str[0] = toupper(str[0]);
		idx++;
	}
	for (int i=1; i < str.length(); i++) {
		if ('A' <= str[i] && str[i] <= 'Z') {
			str[i] = tolower(str[i]);
		}
	}
	cout <<"���� ���:"<< str;
	return 0;
}