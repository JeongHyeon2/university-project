#include<iostream>
#include "Set.h"

using namespace std;



int main()

{

    Set s1, s2;



    cout << "두 정수집합 입력(음수는 한 집합의 끝)" << endl;

    cin >> s1 >> s2;


    cout << "합집합은 : " << (s1 | s2) << endl;

    cout << "교집합은 : " << (s1 & s2) << endl;

    return 0;

}