#include<iostream>
using namespace std;

void showDigit(int n);
void showReverseDigit(int n);
int main() {
    int val;

    cout << "정수 입력: ";
    cin >> val;

    cout << "바로 출력 : ";
    showDigit(val);

    cout << "\n";

    cout << "거꾸로 출력: ";
    showReverseDigit(val);

    return 0;

}
void showDigit(int n) {

  
    int q = n / 10;
    int r = n % 10;
    if (q > 0) {
        showDigit(q);
    }
    cout << r << " ";
    return;


}
void showReverseDigit(int n) {
    
    int q = n / 10;
    int r = n % 10;
    cout << r << " ";
    if (q > 0) {
        showReverseDigit(q);
    }
    return;

}