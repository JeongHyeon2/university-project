#ifndef NODE_HPP
#define NODE_HPP

#include "Node.h"
template<typename T>
Node<T>::Node(T* t, Node<T>* n, Node<T>* p)
{
	next = n;
	data = t;
	prev = p;
}

template<typename T>
Node<T>::Node()
{
	next = nullptr;
	data = nullptr;
	prev = nullptr;
}

template<typename T>
Node<T>::~Node()
{
}

template<typename T>
Node<T>* Node<T>::getNext() const
{
	return next;
}

template<typename T>
void Node<T>::setNext(Node<T>* n)
{
	next = n;
}

template<typename T>
T* Node<T>::getData() const
{
	return data;
}

template<typename T>
void Node<T>::setData(T* t)
{
	data = t;
}

template<typename T>
void Node<T>::setPrev(Node<T>* p)
{
	prev = p;
}

template<typename T>
Node<T>* Node<T>::getPrev() const
{
	return prev;
}

#endif 