//package week_5;
//
//public class Generic_1 {
//
//	public static void main(String[] args)  {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
//  class List<T> {
//	// �ʵ� ����
//	T[] list;
//	int index=0;
//	static int size=5;
//    // ������ ����
//	public List() {
//		this(size);
//	}
//    public List(int size) {
//    	this.size=size;
//    	list = (T []) new Object[size]; }
//    // �޼��� ����
//	public T getAt(int idx) {return list[idx];} // List�� ����� ��ü �߿� idx ��ġ�� ��ü ��ȯ
//	public void setAt(int idx, T ob) {list[idx]=ob;} // List�� idx ��ġ�� ��ü�� ob�� ġȯ
//	public void add(T ob)  { 
//		if(size<=index) {
//			throw new FullException("������ ��á���ϴ�.");
//		}
//		else{list[index]=ob; index++;} }// List�� ���� ��ü ob�� �߰�
//	public void addAt(int idx, T ob) {
//		if(size<=index) {throw new FullException("������ ��á���ϴ�.");}
//		else {for(int i = index-1;i>=idx;i--)
//		{
//			list[i+1]=list[i];
//				}
//		list[idx]=ob;
//		index++;
//	} }// List�� idx ��ġ�� ��ü ob�� �߰�
//	public void deleteAt(int idx) {
//		list[idx]=null; 
//	     for(int i=idx;i<index-1;i++)
//		    {list[i]=list[i+1];
//		    list[i+1]=null;} 
//	     list[index-2]=null;
//	index--;   }// List�� idx ��ġ�� ��ü�� ���� 
//}
//class FullException extends RuntimeException {
//	public FullException() {
//		this("���� �� ��");
//	}
//	public FullException(String exception) {
//		super(exception);
//	}
//}
//
//
