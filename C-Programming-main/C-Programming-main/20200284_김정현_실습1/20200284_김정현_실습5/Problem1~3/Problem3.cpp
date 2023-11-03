#include<iostream>
using namespace std;


void printArr(int* p, int size)

{

	for (int i = 0; i < size; i++)

	{

		for (int j = 0; j < size; j++)

			cout << *(p + (size * i + j)) << " ";

		cout << endl;

	}

}


int main()

{

	const int MAX = 3;

	int arr[MAX][MAX] = { 1,2,3,4,5,6,7,8,9 };

	printArr(*arr, MAX);


	return 0;
	
}
