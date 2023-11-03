#ifndef NODE_H
#define NODE_H
#pragma once
template<typename T>
class Node {
public:
	Node(T* data,Node<T>* next, Node<T>* p);
	Node();
	~Node();
	Node<T>* getNext() const;
	void setNext(Node<T>* next);
	T* getData() const;
	void setData(T* t);
	void setPrev(Node<T>* prev);
	Node<T>* getPrev() const;
private:
	T* data;
	Node<T>* next;
	Node<T>* prev;
	
};
#endif
