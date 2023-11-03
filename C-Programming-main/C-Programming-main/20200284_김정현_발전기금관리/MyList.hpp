#ifndef MYLIST_HPP
#define MYLIST_HPP
#include "MyList.h"

template<typename T>
MyList<T>::MyList()
{
	list = new LinkedList<T>();

	// for �Ϲ����ڵ�
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
	cout << "******������ݰ������α׷�******" << endl;
	readAll();
	cout << endl;
	while (true) {
		cout << "[1]��ü ��ȸ / [2]�ű� ��Ź�� ��� / [3]��Ź���� ���� / [4]��Ź�� ���� / [5]���� :";
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
		default:cout << "�߸��� �Է��Դϴ�!" << endl;

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
	cout << "�ű� ��Ź�� ������ �Է��ϼ���: ";
	string type, name, phNum;
	int dona;
	cin >> type;
	if (type == "�л�") {
		string sId, department;
		cin >> sId >> name >> department >> phNum >> dona;

		if (checkPhoneNumDuplicate(phNum)) {
			cout << ">�ߺ��� ��ȭ��ȣ�Դϴ�!!" << endl;
		}
		else if (checkStudentIdDuplicate(sId)) {
			cout << ">�ߺ��� �й��Դϴ�!!" << endl;
		}
		else {
			Student* s = new Student(name, phNum, dona, sId, department);

			add(s);
			cout << ">��� ����!" << endl;
		}
	}
	else if (type == "������") {
		string sId, department, extension;
		cin >> sId >> name >> department >> extension >> phNum >> dona;
		if (checkPhoneNumDuplicate(phNum)) {
			cout << ">�ߺ��� ��ȭ��ȣ�Դϴ�!!" << endl;
		}
		else if (checkStaffIdDuplicate(sId)) {
			cout << ">�ߺ��� ����Դϴ�!!" << endl;
		}
		else {
			Staff* s = new Staff(name, phNum, dona, sId, department, extension);
			add(s);
			cout << ">��� ����!" << endl;
		}
	}
	else if (type == "�Ϲ�") {
		cin >> name >> phNum >> dona;
		if (checkPhoneNumDuplicate(phNum)) {
			cout << ">�ߺ��� ��ȭ��ȣ�Դϴ�!!" << endl;
		}
		else {
			OrdinaryPerson* o = new OrdinaryPerson(name, phNum, dona, ordinaryPersonCode);
			add(o);
			cout << ">��� ����!" << endl;
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
void MyList<T>::setOrdinaryPersonCode() //�Ϲ����ڵ�++
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
	string tmp1 = ordinaryPersonCode; //�����ִ� �ڵ�
	string tmp2 = key;	//���ε��� �ڵ�
	int num1 = stoi(tmp1.erase(0, ORDINARY_PERSON_CODE.length())); //���� ���ĺ� ����
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
	cout << "������ ���ϴ� ����̳� �й� �Ǵ� ��ȭ��ȣ�� �Է��ϼ��� :";
	string key;
	int dona;
	cin >> key;
	cout << "�߰� ��Ź���� �Է��ϼ��� :";
	cin >> dona;

	Node<Person>* tmp = list->getFrist();

	for (int i = 0; i < list->getSize(); i++) {
		if (tmp->getData()->getKey() == key || tmp->getData()->getPhoneNum() == key) {

			tmp->getData()->setDonation(tmp->getData()->getDonation() + dona);

			tmp->getPrev()->setNext(tmp->getNext());
			if (tmp->getNext() != nullptr) tmp->getNext()->setPrev(tmp->getPrev());

			list->setSize(list->getSize() - 1);
			add(tmp->getData());
			cout << "<����>" << tmp->getData()->print() << endl;
			return;
		}
		tmp = tmp->getNext();
	}
	cout << "�������� �ʽ��ϴ�!!" << endl;
}

template<typename T>
void MyList<T>::deletePerson()
{
	cout << "������ ���ϴ� ����̳� �й� �Ǵ� ��ȭ��ȣ�� �Է��ϼ��� :";
	string key;
	cin >> key;
	Node<Person>* tmp = list->getFrist();
	for (int i = 0; i < list->getSize(); i++) {
		if (tmp->getData()->getKey() == key || tmp->getData()->getPhoneNum() == key) {
			cout << "<����>" << tmp->getData()->print() << endl;
			list->remove(tmp);
			return;
		}
		tmp = tmp->getNext();
	}
	cout << "�������� �ʽ��ϴ�!!" << endl;
}

template<typename T>
void MyList<T>::exitProgram(const string& path) const
{
	cout << "���� ������ " <<path <<"�� �����Ϸ��� W/w, ���� ���¸� �����Ϸ��� C/c :";
	char c;
	cin >> c;
	c = tolower(c);
	if (c == 'w') {
		saveFile(path);
		cout << ">>���� ���� ���� �� ����<<" << endl;
		return;
	}
	cout << ">>���� ���� �ʰ� ����<<" << endl;
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
		if (type == "�л�") {
			string sId, department;
			inStream >> sId >> name >> department >> phNum >> dona;
			Student* s = new Student(name, phNum, dona, sId, department);
			add(s);

		}
		else if (type == "������") {
			string sId, department, extension;
			inStream >> sId >> name >> department >> extension >> phNum >> dona;
			Staff* s = new Staff(name, phNum, dona, sId, department, extension);
			add(s);
		}
		else if (type == "�Ϲ�") {
			string key;
			inStream >> key>>name >> phNum >> dona;
			OrdinaryPerson* o = new OrdinaryPerson(name, phNum, dona, key);
			add(o);
			setOrdinaryPersonCode(key);
		}
	}
}

#endif



