package assignment_10;

public class Shape {
	private double size;
	private int key;
	private int shapeType; // 1.삼각형 2.사각형 3.원

	public Shape() {

	}

	public int getShapeType() {
		return shapeType;
	}

	public void setShapeType(int st) {
		this.shapeType = st;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}

class Triangle extends Shape {
	private int width;
	private int height;

	public Triangle(int k, int w, int h) {
		width = w;
		height = h;
		setKey(k);
		setSize(width * height / 2);
		setShapeType(1);

	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

class Rectangle extends Shape {
	private int width;
	private int height;

	public Rectangle(int k, int w, int h) {
		width = w;
		height = h;
		setKey(k);
		setSize(width * height);
		setShapeType(2);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

class Circle extends Shape {
	private int radius;
	private final double PI = 3.14;

	public Circle(int k, int r) {
		radius = r;
		setKey(k);
		setSize(radius * radius * PI);
		setShapeType(3);

	}

	public void setRadius(int r) {
		this.radius = r;
	}

	public int getRadius() {
		return radius;
	}
}
