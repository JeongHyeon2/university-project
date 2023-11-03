package assignment_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class ShapeManager {
	private ShapeList slist = new ShapeList();
	private Scanner sc = new Scanner(System.in);

	public void run() {
		readFile("test.txt");
		while (true) {
			System.out.print("Select Menu (1. Input, 2. Delete, 3. Print, 4. Draw, 5. Sort, 6. Statistics, 7. Exit) ?");
			switch (sc.nextInt()) {
			case 1:
				input();
				System.out.println();
				break;
			case 2:
				delete();
				System.out.println();
				break;

			case 3:
				System.out.println(slist.printAll());
				break;

			case 4:
				draw();
				break;

			case 5:
				System.out.println(slist.sort());
				break;

			case 6:
				System.out.println(slist.statistics());
				break;

			case 7:
				saveFile("test.txt");
				System.out.println("**종료**");
				
				return;

			}

		}
	}

	private void input() {
		System.out.print("Select Object(1. Triangle, 2. Rectangle, 3. Circle) ?");
		try {
			switch (sc.nextInt()) {
			case 1:
				System.out.print("Input Info (id, width, height) ? ");
				slist.add(new Triangle(sc.nextInt(), sc.nextInt(), sc.nextInt()), "key");
				break;
			case 2:
				System.out.print("Input Info (id, width, height) ? ");
				slist.add(new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt()), "key");
				break;
			case 3:
				System.out.print("Input Info (id, radius) ? ");
				slist.add(new Circle(sc.nextInt(), sc.nextInt()), "key");
				break;
			}
			System.out.println("OK");

		} catch (ListFullException e) {
			System.out.println("[Error] Full List");
		} catch (KeyDulicationException e) {
			System.out.println("[Error] Same ID exists");
		}
	}

	private void delete() {
		System.out.print("Input Id? ");
		try {
			slist.delete(sc.nextInt());
			System.out.println("OK");

		} catch (ListEpmtyException e) {
			System.out.println("[Error] List Empty");
		} catch (NotFoundKeyException e) {
			System.out.println("[Error] ID does not exist");

		}
	}

	private void draw() {
		System.out.print("Input Info(id)? ");
		slist.draw(sc.nextInt());
	}

	public void readFile(String path) {// 파일 읽기
		System.out.println("Loaded from " + path);
		File file = new File(path);
		try {
			if (file.isFile()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

				String s = "";
				// 한줄씩 읽어서
				while ((s = br.readLine()) != null) {
					read(s);
				}
				br.close();
			}
		} catch (IOException e) {

		}
	}

	private void read(String s) { // 읽은 파일을 Shape로 변환
		s.trim();
		String[] arr = extractInteger(s);
		try {
			switch (s.charAt(0)) {
			case 'T':
				slist.add(new Triangle(Integer.parseInt(arr[0]), Integer.parseInt(arr[2]), Integer.parseInt(arr[1])),
						"key");
				break;
			case 'R':
				slist.add(new Rectangle(Integer.parseInt(arr[0]), Integer.parseInt(arr[2]), Integer.parseInt(arr[1])),
						"key");
				break;

			case 'C':
				slist.add(new Circle(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])), "key");
				break;

			}
		} catch (Exception e) {

		}

	}

	private String[] extractInteger(String s) { // 숫자 추출
		String str = s.replaceAll("[^0-9,,]", "");
		String[] arr = str.split(",");

		return arr;
	}

	public void saveFile(String path) { // 파일 저장
		System.out.println("Saved to " + path);
		try {
			OutputStream output = new FileOutputStream(path);
			String str = slist.printAll();
			byte[] by = str.getBytes();
			output.write(by);

		} catch (Exception e) {
			e.getStackTrace();
		}
	
	}

}
