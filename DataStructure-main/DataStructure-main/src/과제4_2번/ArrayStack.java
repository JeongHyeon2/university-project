package °úÁ¦4_2¹ø;

import java.util.Random;

public class ArrayStack<E>{
	private int top;
	private int capacity;
	private E[] s;

	public ArrayStack(){
		top=-1;
		capacity=4;
		s= (E[])new Object[capacity];	
	}
	public int getCapacity() {
		return capacity;
	}
	public int getTop() {
		return top;
	}

	public boolean isEmpty() {
		return (top==-1);
	}
	public boolean isFull() {
		return (getCapacity()==top+1);
	}
	public void push(E e) {
		if(isFull()) resize(2*capacity);
		s[++top]=e;
	}
	public E top() {
		if(isEmpty()) return null;
		return s[top];
	}
	public E pop() {
		if(isEmpty()) return null;
		E e = s[top];
		s[top]=null;
		top--;
		return e;
	}
	public void resize(int newsize) {
		E[] tmp = (E[])new Object[newsize];
		for(int i=0;i<top+1;i++) {
			tmp[i]=s[i];
		}
		capacity=newsize;
		s=tmp;
	}

}
