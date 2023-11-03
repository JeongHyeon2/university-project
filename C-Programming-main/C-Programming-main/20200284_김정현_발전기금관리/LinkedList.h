#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#pragma once
#include "Node.hpp"
template<typename T>
class LinkedList {
public:
	LinkedList();
	~LinkedList();

	void addFirst(T* t);
	void insert(T* t, Node<T>* prev);
	void remove(Node<T>* node);
	
	Node<T>* getFrist() const;
	Node<T>* getHead() const;
	int getSize() const;
	void setSize(int size);
private:
	Node<T>* head;
	int size;
};
#endif