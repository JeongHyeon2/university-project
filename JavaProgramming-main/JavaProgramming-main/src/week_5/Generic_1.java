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
//	// 필드 선언
//	T[] list;
//	int index=0;
//	static int size=5;
//    // 생성자 선언
//	public List() {
//		this(size);
//	}
//    public List(int size) {
//    	this.size=size;
//    	list = (T []) new Object[size]; }
//    // 메서드 정의
//	public T getAt(int idx) {return list[idx];} // List에 저장된 객체 중에 idx 위치의 객체 반환
//	public void setAt(int idx, T ob) {list[idx]=ob;} // List의 idx 위치의 객체를 ob로 치환
//	public void add(T ob)  { 
//		if(size<=index) {
//			throw new FullException("스택이 꽉찼습니다.");
//		}
//		else{list[index]=ob; index++;} }// List의 끝에 객체 ob를 추가
//	public void addAt(int idx, T ob) {
//		if(size<=index) {throw new FullException("스택이 꽉찼습니다.");}
//		else {for(int i = index-1;i>=idx;i--)
//		{
//			list[i+1]=list[i];
//				}
//		list[idx]=ob;
//		index++;
//	} }// List의 idx 위치에 객체 ob를 추가
//	public void deleteAt(int idx) {
//		list[idx]=null; 
//	     for(int i=idx;i<index-1;i++)
//		    {list[i]=list[i+1];
//		    list[i+1]=null;} 
//	     list[index-2]=null;
//	index--;   }// List의 idx 위치의 객체를 삭제 
//}
//class FullException extends RuntimeException {
//	public FullException() {
//		this("스택 꽉 참");
//	}
//	public FullException(String exception) {
//		super(exception);
//	}
//}
//
//
