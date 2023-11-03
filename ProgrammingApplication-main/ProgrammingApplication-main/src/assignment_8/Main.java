package assignment_8;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("Select Object (1. Triangle, 2. Rectangle, 3. Circle, 4. Exit): ");
			switch (sc.nextInt()) {
			case 1:
				System.out.print("Input Size(width, height): ");
				Triangle tri = new Triangle(sc.nextDouble(), sc.nextDouble());
				System.out.printf("Size is %.3f%n", tri.getSize());
				break;
			case 2:
				System.out.print("Input Size(width, height): ");
				Rectangle rect = new Rectangle(sc.nextDouble(), sc.nextDouble());
				System.out.printf("Size is %.3f%n", rect.getSize());
				break;
			case 3:
				System.out.print("Input Size(radius): ");
				Circle cir = new Circle(sc.nextDouble());
				System.out.printf("Size is %.3f%n", cir.getSize());
				break;
			case 4:
				System.out.println("**종료**");
				return;
			default:
				System.out.println("잘못된 입력");
			}
		}
	}

}

class Triangle {
	private double width;
	private double height;

	public Triangle(double w, double h) {
		width = w;
		height = h;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getSize() {
		return this.height * this.width / 2;
	}

}

class Rectangle {
	private double width;
	private double height;

	public Rectangle(double w, double h) {
		width = w;
		height = h;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getSize() {
		return this.height * this.width;
	}

}

class Circle {
	private double radius;

	public Circle(double r) {
		radius = r;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public double getSize() {
		return radius * radius * Math.PI;
	}
}
