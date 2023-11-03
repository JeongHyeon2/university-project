package assignment_10;

public class ShapeList {
	private Shape[] slist;
	private int top;

	public ShapeList() {
		slist = new Shape[100];
		top = -1;
	}

	public int getsize() {
		return top + 1;
	}


	public void add(Shape s, String type) throws ListFullException, KeyDulicationException {
		if (top == 99)
			throw new ListFullException();

		if (isEmpty()) {
			slist[top + 1] = s;
			top++;
			return;
		}
		if (compareTo(slist[0], s, type) > 0) { // 첫번째 id 보다 작을때
			insert(0, s);
			return;
		}
		if (compareTo(slist[top], s, type) < 0) { // 맨마지막 id보다 클때
			slist[top + 1] = s;
			top++;
			return;
		}
		for (int i = 0; i < top; i++) { // i와 i+1 사이에 있을때
			if (compareTo(slist[i], s, type) < 0 && compareTo(slist[i + 1], s, type) > 0) {
				insert(i + 1, s); // 끼워넣기
				return;
			}
		}
		throw new KeyDulicationException();
	}

	private void insert(int i, Shape s) { // i번째에 p 끼워넣기
		for (int j = top; j >= i; j--) {
			slist[j + 1] = slist[j];
		}
		slist[i] = s;
		top++;
	}

	public void delete(int k) throws ListEpmtyException, NotFoundKeyException  {
		if (top == -1)
			throw new ListEpmtyException();

		for (int index = 0; index < top + 1; index++) {
			if (slist[index].getKey() == k) { // id 발견
				for (; index < top; index++) { // 쉬프트
					slist[index] = slist[index + 1];
				}
				top--;
				return;
			}
		} // 찾지 못하면 예외
		throw new NotFoundKeyException();

	}

	private boolean isEmpty() {
		return top == -1;
	}

	public String printAll() {
		String s = "";
		for (int i = 0; i <= top; i++) {
			s += printLine(i) + "\n";
		}
		return s;

	}

	private String printLine(int i) { // 1줄 출력
		String s = "";

		switch (slist[i].getShapeType()) {
		case 1:
			Triangle t = (Triangle) slist[i];
			s = "Triangle  (" + t.getKey() + ", " + t.getWidth() + ", " + t.getHeight() + ", " + t.getSize() + ")";
			break;
		case 2:
			Rectangle r = (Rectangle) slist[i];
			s = "Rectangle (" + r.getKey() + ", " + r.getWidth() + ", " + r.getHeight() + ", " + r.getSize() + ")";
			break;
		case 3:
			Circle c = (Circle) slist[i];
			s = "Circle    (" + c.getKey() + ", " + c.getRadius() + ", " + c.getSize() + ")";
			break;
		}
		return s;
	}

	public String statistics() {
		String result = "";
		double avgAll = 0, avgTri = 0, avgRect = 0, avgCir = 0;
		int numTri = 0, numRect = 0, numCir = 0;

		for (int i = 0; i < top + 1; i++) {
			avgAll += slist[i].getSize();
			if (slist[i].getShapeType() == 1) {
				avgTri += slist[i].getSize();
				numTri++;

			} else if (slist[i].getShapeType() == 2) {
				avgRect += slist[i].getSize();
				numRect++;

			} else {
				avgCir += slist[i].getSize();
				numCir++;
			}
		}
		avgAll /= (top + 1);
		avgTri /= numTri;
		avgRect /= numRect;
		avgCir /= numCir;
		result += "Number of Objects = " + (top + 1) + ", Average size of Objects = " + avgAll + "\n";
		result += "Number of Triangle = " + numTri + ", Average size of Triangles = " + avgTri + "\n";
		result += "Number of Rectangle = " + numRect + ", Average size of Rectangle = " + avgRect + "\n";
		result += "Number of Circle = " + numCir + ", Average size of Circle = " + avgCir + "\n";
		return result;
	}

	public void draw(int num) {
		int key = find(num);
		switch (slist[key].getShapeType()) {
		case 1:
			Triangle tri = (Triangle) slist[key];
			drawTri(tri.getWidth(), tri.getHeight());
			break;
		case 2:
			Rectangle rect = (Rectangle) slist[key];
			drawRect(rect.getWidth(), rect.getHeight());
			break;
		case 3:
			Circle cir = (Circle) slist[key];
			drawCir(cir.getRadius());
			break;
		}
	}

	private void drawTri(int w, int h) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (j<=i)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	private void drawRect(int w, int h) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private void drawCir(int r) {
		for (int i = -r; i <= r; i++) {
			for (int j = -r; j <= r; j++) {
				if (i * i + j * j <= r * r) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	private int find(int num) { //key 값과 일치하는 Shape 객체의 인덱스 반환
		for (int i = 0; i < top + 1; i++) {
			if (slist[i].getKey() == num)
				return i;
		}
		return -1;
	}

	public String sort() {
		ShapeList sl = new ShapeList();
		for (int i = 0; i < top + 1; i++) {
			try {
				sl.add(slist[i], "size");
			} catch (ListFullException e) {

			} catch (KeyDulicationException e) {

			}
		}
		return (sl.printAll());
	}

	private int compareTo(Shape s1, Shape s2, String type) {
		if (type.equals("size")) {
			if (s1.getSize() < s2.getSize())
				return -1;
			else if (s1.getSize() > s2.getSize())
				return 1;
			return 0;
		} else {
			if (s1.getKey() < s2.getKey())
				return -1;
			else if (s1.getKey() > s2.getKey())
				return 1;
			return 0;
		}
	}
}

class ListFullException extends Exception {

}

class ListEpmtyException extends Exception {

}

class KeyDulicationException extends Exception {

}
class NotFoundKeyException extends Exception{
	
}
