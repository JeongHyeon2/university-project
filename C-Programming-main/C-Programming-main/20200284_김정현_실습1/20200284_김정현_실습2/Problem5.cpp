#include<iostream>
using namespace std;

void setArray(int arr[], int idx);
void merge(int arr1[], int arr2[], int mergeArr[]);

const int ARRAY_SIZE = 5;

int main() {

	int set1[ARRAY_SIZE];
	int set2[ARRAY_SIZE];
	int resultSet[2 * ARRAY_SIZE];
	cout << "�������� ���� " << ARRAY_SIZE << "�� �Է�: ";
	for (int i = 0; i < ARRAY_SIZE; i++) {
		setArray(set1, i);
	}
	cout << "�������� ���� " << ARRAY_SIZE << "�� �Է� : ";
	for (int i = 0; i < ARRAY_SIZE; i++) {
		setArray(set2, i);
	}
	merge(set1, set2, resultSet);
	for (int i = 0; i < 2 * ARRAY_SIZE; i++) cout << resultSet[i] << " ";

}
void setArray(int arr[], int i) {
	int num;
	cin >> num;
	arr[i] = num;
}

void merge(int arr1[], int arr2[], int mergeArr[]) {
	/*mergeArr�� return�ϴ� ���·� �����ϸ� �̹� main���� merge�� �Ϸ��� �迭��
	���� �迭�� ���� ���ֱ� ������ return�� �ؼ� �迭�� �ٲٷ��� �ϸ� ������ ������ �߻���.*/

	int idx1 = 0, idx2 = 0, resultIdx = 0;

	while (1) {
		if (arr1[idx1] > arr2[idx2])
			mergeArr[resultIdx++] = arr2[idx2++];
		else
			mergeArr[resultIdx++] = arr1[idx1++];

		if (idx1 == ARRAY_SIZE || idx2 == ARRAY_SIZE) break;
	}
	if (idx1 < ARRAY_SIZE)
		for (; idx1 < ARRAY_SIZE; idx1++)
			mergeArr[resultIdx++] = arr1[idx1];
	else
		for (; idx2 < ARRAY_SIZE; idx2++)
			mergeArr[resultIdx++] = arr2[idx2];

}