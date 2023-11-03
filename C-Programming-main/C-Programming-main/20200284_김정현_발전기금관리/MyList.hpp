#ifndef MYLIST_HPP
#define MYLIST_HPP
#include "MyList.h"

template<typename T>
MyList<T>::MyList()
{
	list = new LinkedList<T>();

	// for 일반인코드
	int num = 1;
	ordinaryPersonCode = ORDINARY_PERSON_CODE;
	ordinaryPersonCode.insert(ORDINARY_PERSON_CODE.length(), ORDINARY_PERSON_CODE_LENGTH - ORDINARY_PERSON_CODE.length() - to_string(num).length(), '0');
	ordinaryPersonCode.append(to_string(num));
}

template<typename T>
MyList<T>::~MyList()
{
	delete list;
}

template<typename T>
void MyList<T>::run(const string& path)
{
	loadFile(path);
	cout << "******발전기금관리프로그램******" << endl;
	readAll();
	cout << endl;
	while (true) {
		cout << "[1]전체 조회 / [2]신규 기탁자 등록 / [3]기탁정보 변경 / [4]기탁자 삭제 / [5]종료 :";
		int menu;
		cin >> menu;
		switch (menu)
		{
		case 1: readAll();
			cout << endl;
			break;
		case 2: registerPerson();
			cout << endl;
			break;
		case 3: updatePerson();
			cout << endl;
			break;
		case 4: deletePerson();
			cout << endl;
			break;
		case 5: exitProgram(path);
			return;
			break;
		default:cout << "잘못된 입력입니다!" << endl;

		}
	}

}

template<typename T>
void MyList<T>::readAll() const
{
	Node<T>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		cout << tmp->getData()->print() << endl;
		tmp = tmp->getNext();
	}
}

template<typename T>
void MyList<T>::registerPerson()
{
	cout << "신규 기탁자 정보를 입력하세요: ";
	string type, name, phNum;
	int dona;
	cin >> type;
	if (type == "학생") {
		string sId, department;
		cin >> sId >> name >> department >> phNum >> dona;

		if (checkPhoneNumDuplicate(phNum)) {
			cout << ">중복된 전화번호입니다!!" << endl;
		}
		else if (checkStudentIdDuplicate(sId)) {
			cout << ">중복된 학번입니다!!" << endl;
		}
		else {
			Student* s = new Student(name, phNum, dona, sId, department);

			add(s);
			cout << ">등록 성공!" << endl;
		}
	}
	else if (type == "교직원") {
		string sId, department, extension;
		cin >> sId >> name >> department >> extension >> phNum >> dona;
		if (checkPhoneNumDuplicate(phNum)) {
			cout << ">중복된 전화번호입니다!!" << endl;
		}
		else if (checkStaffIdDuplicate(sId)) {
			cout << ">중복된 사번입니다!!" << endl;
		}
		else {
			Staff* s = new Staff(name, phNum, dona, sId, department, extension);
			add(s);
			cout << ">등록 성공!" << endl;
		}
	}
	else if (type == "일반") {
		cin >> name >> phNum >> dona;
		if (checkPhoneNumDuplicate(phNum)) {
			cout << ">중복된 전화번호입니다!!" << endl;
		}
		else {
			OrdinaryPerson* o = new OrdinaryPerson(name, phNum, dona, ordinaryPersonCode);
			add(o);
			cout << ">등록 성공!" << endl;
			setOrdinaryPersonCode();
		}
	}
}

template<typename T>
void MyList<T>::add(T* t)
{
	if (list->getSize() == 0) {
		list->addFirst(t);
	}
	else {
		Node<T>* tmp = list->getFrist();
		for (int i = 0; i < list->getSize(); i++) {
			if (priorityDecision(t, tmp->getData())) {
				list->insert(t, tmp->getPrev());
				return;
			}
			if (tmp->getNext() != nullptr) tmp = tmp->getNext();
		}
		list->insert(t, tmp);
	}

}

template<typename T>
bool MyList<T>::priorityDecision(const Person* t1, const Person* t2) const
{
	if (t1->getDonation() > t2->getDonation()) {
		return true;
	}
	else if (t1->getDonation() < t2->getDonation()) {
		return false;
	}
	else {
		if (t1->getName() < t2->getName()) {
			return true;
		}
		else if (t1->getName() > t2->getName()) {
			return false;
		}
		else {
			if (t1->getPhoneNum() < t2->getPhoneNum()) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}

template<typename T>
bool MyList<T>::checkPhoneNumDuplicate(const string& phNum) const
{
	Node<Person>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		if (phNum == tmp->getData()->getPhoneNum()) {
			return true;
		}
		tmp = tmp->getNext();
	}
	return false;
}

template<typename T>
bool MyList<T>::checkStudentIdDuplicate(const string& sId) const
{
	Node<Person>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		if (sId == tmp->getData()->getKey()) {
			return true;
		}
		tmp = tmp->getNext();
	}
	return false;
}

template<typename T>
bool MyList<T>::checkStaffIdDuplicate(const string& sId) const
{
	Node<Person>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		if (sId == tmp->getData()->getKey()) {
			return true;
		}
	}
	return false;
}

template<typename T>
void MyList<T>::setOrdinaryPersonCode() //일반인코드++
{
	int num = stoi(ordinaryPersonCode.erase(0, ORDINARY_PERSON_CODE.length()));
	num++;
	ordinaryPersonCode = ORDINARY_PERSON_CODE;
	ordinaryPersonCode.insert(ORDINARY_PERSON_CODE.length(), ORDINARY_PERSON_CODE_LENGTH - ORDINARY_PERSON_CODE.length() - to_string(num).length(), '0');
	ordinaryPersonCode.append(to_string(num));
}
template<typename T>
void MyList<T>::setOrdinaryPersonCode(const string& key)
{
	string tmp1 = ordinaryPersonCode; //원래있던 코드
	string tmp2 = key;	//새로들어온 코드
	int num1 = stoi(tmp1.erase(0, ORDINARY_PERSON_CODE.length())); //앞의 알파벳 제거
	int num2 = stoi(tmp2.erase(0, ORDINARY_PERSON_CODE.length()));
	if (num1 < num2) {
		num2++;
		ordinaryPersonCode = ORDINARY_PERSON_CODE;
		ordinaryPersonCode.insert(ORDINARY_PERSON_CODE.length(), ORDINARY_PERSON_CODE_LENGTH - ORDINARY_PERSON_CODE.length() - to_string(num2).length(), '0');
		ordinaryPersonCode.append(to_string(num2));
	}
	else if (num1 == num2) {
		setOrdinaryPersonCode();
	}
	
}

template<typename T>
void MyList<T>::updatePerson()
{
	cout << "변경을 원하는 사번이나 학번 또는 전화번호를 입력하세요 :";
	string key;
	int dona;
	cin >> key;
	cout << "추가 기탁액을 입력하세요 :";
	cin >> dona;

	Node<Person>* tmp = list->getFrist();

	for (int i = 0; i < list->getSize(); i++) {
		if (tmp->getData()->getKey() == key || tmp->getData()->getPhoneNum() == key) {

			tmp->getData()->setDonation(tmp->getData()->getDonation() + dona);

			tmp->getPrev()->setNext(tmp->getNext());
			if (tmp->getNext() != nullptr) tmp->getNext()->setPrev(tmp->getPrev());

			list->setSize(list->getSize() - 1);
			add(tmp->getData());
			cout << "<변경>" << tmp->getData()->print() << endl;
			return;
		}
		tmp = tmp->getNext();
	}
	cout << "존재하지 않습니다!!" << endl;
}

template<typename T>
void MyList<T>::deletePerson()
{
	cout << "삭제를 원하는 사번이나 학번 또는 전화번호를 입력하세요 :";
	string key;
	cin >> key;
	Node<Person>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		if (tmp->getData()->getKey() == key || tmp->getData()->getPhoneNum() == key) {
			cout << "<삭제>" << tmp->getData()->print() << endl;
			list->remove(tmp);
			return;
		}
		tmp = tmp->getNext();
	}
	cout << "존재하지 않습니다!!" << endl;
}

template<typename T>
void MyList<T>::exitProgram(const string& path) const
{
	cout << "변경 사항을 " <<path <<"에 저장하려면 W/w, 이전 상태를 유지하려면 C/c :";
	char c;
	cin >> c;
	c = tolower(c);
	if (c == 'w') {
		saveFile(path);
		cout << ">>변경 사항 저장 후 종료<<" << endl;
		return;
	}
	cout << ">>저장 하지 않고 종료<<" << endl;
	return;
}

template<typename T>
void MyList<T>::saveFile(const string& path) const
{
	ofstream outStream;
	outStream.open(path);
	Node<Person>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		outStream << tmp->getData()->toString() + "\n";
		tmp = tmp->getNext();
	}

}

template<typename T>
void MyList<T>::loadFile(const string& path)
{
	ifstream inStream;

	inStream.open(path);
	while (!inStream.eof()) {
		string type, name, phNum;
		int dona;
		inStream >> type;
		if (type == "학생") {
			string sId, department;
			inStream >> sId >> name >> department >> phNum >> dona;
			Student* s = new Student(name, phNum, dona, sId, department);
			add(s);

		}
		else if (type == "교직원") {
			string sId, department, extension;
			inStream >> sId >> name >> department >> extension >> phNum >> dona;
			Staff* s = new Staff(name, phNum, dona, sId, department, extension);
			add(s);
		}
		else if (type == "일반") {
			string key;
			inStream >> key>>name >> phNum >> dona;
			OrdinaryPerson* o = new OrdinaryPerson(name, phNum, dona, key);
			add(o);
			setOrdinaryPersonCode(key);
		}
	}
}

#endif



