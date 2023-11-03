#ifndef LINKEDLIST_HPP
#define LINKEDLIST_HPP
#include "LinkedList.h"
template<typename T>
LinkedList<T>::LinkedList()
{
	head = new Node<T>();
	size = 0;
}

template<typename T>
LinkedList<T>::~LinkedList()
{
	if (size >= 1) {
		Node<T>* tmp = getFrist();
		delete head;
		for (int i = 0; i < size - 1; i++) {
			tmp = tmp->getNext();
			delete tmp->getPrev()->getData();
			delete tmp->getPrev();
		}
		delete tmp->getData();
		delete tmp;
	}
	else {
		delete head;
	}
}

template<typename T>
void  LinkedList<T>::addFirst(T* t)
{
	if (size == 0) {
		head->setNext(new Node<T>(t, nullptr, head));
	}
	else {
		Node<T>* tmp = new Node<T>(t, head->getNext(), head);
		head->getNext()->setPrev(tmp);
		head->setNext(tmp);
	}
	size++;
}

template<typename T>
void LinkedList<T>::insert(T* t, Node<T>* prev)
{
	if (prev == head) {
		addFirst(t);
	}
	else {
		Node<T>* tmp = new Node<T>(t, prev->getNext(), prev);
		if (prev->getNext() != nullptr) prev->getNext()->setPrev(tmp);
		prev->setNext(tmp);
		size++;
	}

}

template<typename T>
void LinkedList<T>::remove(Node<T>* node)
{
	if (size == 0) return;
	else
	{
		Node<T>* tmp = getFrist();
		for (int i = 0; i < size; i++) {
			if (tmp == node) {
				tmp->getPrev()->setNext(tmp->getNext());
				if (tmp->getNext() != nullptr) tmp->getNext()->setPrev(tmp->getPrev());
				delete tmp->getData();
				delete tmp;
				size--;
				return;
			}
			if (tmp != nullptr) tmp = tmp->getNext();
		}
	}

}

template<typename T>
Node<T>* LinkedList<T>::getFrist() const
{
	return head->getNext();
}

template<typename T>
Node<T>* LinkedList<T>::getHead() const
{
	return head;
}

template<typename T>
int LinkedList<T>::getSize() const
{
	return size;
}

template<typename T>
void LinkedList<T>::setSize(int s)
{
	size = s;
}
#endif