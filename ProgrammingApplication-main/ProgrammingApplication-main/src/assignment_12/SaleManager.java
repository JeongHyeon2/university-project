package assignment_12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class SaleManager {
	
	public static void main(String[] args) {
		String str = "aaa 1234, ^&*22 33  pp";
		String intStr = str.replaceAll("[]","");
		System.out.println(intStr);
	}
	

	private Scanner sc;
	private PersonList personList;
	private FoodList foodList;
	private OrderList orderList;
	private boolean isFirst;

	public SaleManager() {
		sc = new Scanner(System.in);
		personList = new PersonList();
		foodList = new FoodList();
		orderList = new OrderList();
		isFirst = true;
	}

	public void run() {

		if (isFirst) {
			readFile("test2.txt");
			isFirst = false;
		}

		System.out.print("1. �������, 2. �ǸŸ��, 3. ���� > ");
		switch (sc.nextLine()) {
		case "1":
			manageMode();
			break;
		case "2":
			saleMode();
			break;
		case "3":
			System.out.println("����");
			saveFile("test2.txt");
			return;
		}

	}

	// �������
	private void manageMode() {
		System.out.print("1. �������, 2. ���İ���, 3. �ǸŰ���, 4. �����ܰ� > ");
		switch (sc.nextLine()) {
		case "1":
			personManager();
			break;
		case "2":
			foodManager();
			break;
		case "3":
			saleManager();
			break;
		case "4":
			run();
			return;

		}
	}

	// �ǸŸ��
	private void saleMode() {
		while (true) {
			System.out.print("1. �����Ǹ�, 2. �����ܰ� > ");
			switch (sc.nextLine()) {
			case "1":
				foodSell();
				break;
			case "2":
				run();
				return;
			}
		}
	}

	// ���� �Ǹ�
	private void foodSell() {
		System.out.print(">Input Info (Person, Food, Number) ? ");

		try {
			sell(sc.nextLine());
			System.out.println(">OK");

		} catch (NotFoundNameException e) {

		} catch (Exception e) {
			System.out.println("[Error] Input Error");
		}

	}

	// �Ǹ�
	private void sell(String str) throws NotFoundNameException {
		String[] s = str.trim().split(",");
		try {
			orderList.input(new Order(personList.getList()[find(s[0], "Person")],
					foodList.getList()[find(s[1], "Food")], Integer.parseInt(s[2])));
		} catch (NotFoundNameException e) {
			System.out.println("[Error] " + s[0] + " is not registered or " + s[1] + " is not registered");
			throw new NotFoundNameException();
		}

	}

	// ���ϴ� �̸� index ã��
	private int find(String name, String list) throws NotFoundNameException {
		if (list.equals("Person")) {
			for (int i = 0; i < personList.getLast() + 1; i++) {
				if (personList.getList()[i].getName().equals(name)) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < foodList.getLast() + 1; i++) {
				if (foodList.getList()[i].getName().equals(name)) {
					return i;
				}

			}

		}
		throw new NotFoundNameException();
	}

	// �������
	private void personManager() {
		while (true) {
			System.out.print("1. ������, 2. �������, 3. ������, 4. �����ܰ� > ");
			switch (sc.nextLine()) {
			case "1":
				personRegister();
				break;
			case "2":
				personDelete();
				break;
			case "3":
				for (int i = 0; i < personList.getLast() + 1; i++)
					System.out.println(print("Person", i));
				break;
			case "4":
				manageMode();
				return;

			}
		}
	}

	// ������
	private void personRegister() {
		System.out.print(">Input Person (Name, Phone number) ?");
		String[] s = sc.nextLine().trim().split(",");
		try {
			personList.input(new Person(s[0], s[1]));
			System.out.println(">OK");
		} catch (FullListException e) {
			System.out.println("[Error] FullList");
		} catch (DuplicationNameException e) {
			System.out.println("[Error] " + s[0] + " is in-use");
		} catch (Exception e) {
			System.out.println("[Error] Input Error");
		}
	}

	// �������
	private void personDelete() {
		System.out.print(">Delete Person (Name) ?");
		String name = sc.nextLine().trim();
		try {
			personList.delete(name);
			orderDeletePerson(name);
			System.out.println(">OK");
		} catch (NotFoundNameException e) {
			System.out.println("[Error] " + name + " is not registered");
		}
	}

	// �ֹ�list���� ��� ����
	private void orderDeletePerson(String name) throws NotFoundNameException {
		for (int i = 0; i < orderList.getLast() + 1; i++) {
			if (name.equals(orderList.getList()[i].getPerson().getName())) {
				orderList.delete(orderList.getList()[i].getPerson().getName());
				i--;
			}
		}
	}

	// �ֹ�list���� ���� ����
	private void orderDeleteFood(String name) throws NotFoundNameException {

		for (int i = 0; i < orderList.getLast() + 1; i++) {
			if (name.equals(orderList.getList()[i].getFood().getName())) {
				orderList.delete(orderList.getList()[i].getFood().getName());
				i--;
			}
		}
	}

	// ���� ����
	private void foodManager() {
		while (true) {
			System.out.print("1. ���ĵ��, 2. ���Ļ���, 3. �������, 4. �����ܰ� > ");
			switch (sc.nextLine()) {
			case "1":
				foodRegister();
				break;
			case "2":
				foodDelete();
				break;
			case "3":
				for (int i = 0; i < foodList.getLast() + 1; i++)
					System.out.println(print("Food", i));
				break;
			case "4":
				manageMode();
				return;

			}
		}

	}

	// ���ĵ��
	private void foodRegister() {
		System.out.print(">Input Person (Name, Price) ?");
		String[] s = sc.nextLine().trim().split(",");
		try {
			foodList.input(new Food(s[0], Integer.parseInt(s[1])));
			System.out.println(">OK");
		} catch (FullListException e) {
			System.out.println("[Error] FullList");
		} catch (DuplicationNameException e) {
			System.out.println("[Error] " + s[0] + " is in-use");
		} catch (Exception e) {
			System.out.println("[Error] Input Error");
		}
	}

	// ���� ����
	private void foodDelete() {
		System.out.print(">Delete Food (Name) ?");
		String name = sc.nextLine().trim();
		try {
			foodList.delete(name);
			orderDeleteFood(name);
			System.out.println(">OK");
		} catch (NotFoundNameException e) {
			System.out.println("[Error] " + name + " is not registered");
		}
	}

	// ���
	private String print(String type, int i) {
		switch (type) {
		case "Person":
			return personList.getList()[i].getName() + ", " + personList.getList()[i].getPhoneNum();
		case "Food":
			return foodList.getList()[i].getName() + ", " + foodList.getList()[i].getPrice();

		case "Order":
			return orderList.getList()[i].getPerson().getName() + ", " + orderList.getList()[i].getFood().getName()
					+ ", " + orderList.getList()[i].getAmount();
		}
		return "";
	}

	// �ǸŰ���
	private void saleManager() {
		while (true) {
			System.out.print("1. ���ĺ� �Ǹ����, 2. ����� �Ǹ����, 3. ����� �������, 4. �����ܰ� > ");
			switch (sc.nextLine()) {
			case "1":
				foodSellStatistics();
				break;
			case "2":
				personSellStatistics();
				break;
			case "3":
				personPurchaseStatistics();
				break;
			case "4":
				manageMode();
				return;

			}
		}

	}

	// �������
	private void foodSellStatistics() {
		int[] foodCount = new int[foodList.getLast() + 1];

		for (int i = 0; i < foodList.getLast() + 1; i++) {
			for (int j = 0; j < orderList.getLast() + 1; j++) {
				if (foodList.getList()[i].getName().equals(orderList.getList()[j].getFood().getName())) {
					foodCount[i] += orderList.getList()[j].getAmount();
				}
			}
		}
		for (int i = 0; i < foodList.getLast() + 1; i++) {
			System.out.println(">" + foodList.getList()[i].getName() + ", " + foodCount[i] + "��, "
					+ foodList.getList()[i].getPrice() * foodCount[i] + "��");
		}
	}

	// ����� �Ǹ����
	private void personSellStatistics() {
		int[] personCount = new int[personList.getLast() + 1];
		int[] personAmount = new int[personList.getLast() + 1];

		for (int i = 0; i < personList.getLast() + 1; i++) {
			for (int j = 0; j < orderList.getLast() + 1; j++) {
				if (personList.getList()[i].getName().equals(orderList.getList()[j].getPerson().getName())) {
					personCount[i] += orderList.getList()[j].getAmount();
					personAmount[i] += orderList.getList()[j].getFood().getPrice() * orderList.getList()[j].getAmount();
				}
			}
		}
		for (int i = 0; i < personList.getLast() + 1; i++) {
			System.out.println(
					">" + personList.getList()[i].getName() + ", " + personCount[i] + "��, " + personAmount[i] + "��");
		}

		int count = 0, amount = 0;
		for (int i = 0; i < personList.getLast() + 1; i++) {
			count += personCount[i];
			amount += personAmount[i];
		}
		System.out.println("-Total " + count + "��, " + amount + "��-");

	}

	// ����� ���� ���
	private void personPurchaseStatistics() {
		System.out.print(">Select Person(Name) ? ");
		String name = sc.nextLine();
		try {
			int a = find(name, "Person");

			int[] foodCount = new int[foodList.getLast() + 1];
			for (int i = 0; i < orderList.getLast() + 1; i++) {
				if (name.equals(orderList.getList()[i].getPerson().getName())) {
					for (int j = 0; j < foodList.getLast() + 1; j++) {
						if (orderList.getList()[i].getFood().getName().equals(foodList.getList()[j].getName()))
							foodCount[j] += orderList.getList()[i].getAmount();
					}
				}
			}
			for (int i = 0; i < foodList.getLast() + 1; i++) {
				System.out.println(">" + foodList.getList()[i].getName() + ", " + foodCount[i] + "��, "
						+ foodList.getList()[i].getPrice() * foodCount[i] + "��");

			}
			int count = 0, amount = 0;
			for (int i = 0; i < foodList.getLast() + 1; i++) {
				count += foodCount[i];
				amount += foodList.getList()[i].getPrice() * foodCount[i];
			}
			System.out.println("-Total " + count + "��, " + amount + "��-");
		} catch (NotFoundNameException e) {
			System.out.println("[Error] " + name + " is not registerd");
		}
	}
	
	// ���� �б�

	public void readFile(String path) {
		System.out.println("Loaded from " + path);
		File file = new File(path);
		try {
			if (file.isFile()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
				String s = "";
				while ((s = br.readLine()) != null) {
					read(s);
				}
				br.close();
			}
		} catch (IOException e) {

		}
	}

	// ���پ� �о ���
	public void read(String s) {
		String[] str1 = s.split(">");
		String[] str2 = str1[1].split(",");
		try {
			switch (s.charAt(0)) {
			case 'P':
				personList.input(new Person(str2[0].trim(), str2[1].trim()));
				break;
			case 'F':
				foodList.input(new Food(str2[0].trim(), Integer.parseInt(str2[1].trim())));
				break;
			case 'O':
				String str = str2[0].trim() + "," + str2[1].trim() + "," + str2[2].trim();
				sell(str);
				break;
			}
		} catch (Exception e) {

		}
	}

	public void saveFile(String path) { // ���� ����
		System.out.println("Saved to " + path);
		try {
			OutputStream output = new FileOutputStream(path);

			for (int i = 0; i < personList.getLast() + 1; i++) {

				String str = "Person > " + print("Person", i) + "\n";
				byte[] by = str.getBytes();
				output.write(by);
			}
			for (int i = 0; i < foodList.getLast() + 1; i++) {

				String str = "Food > " + print("Food", i) + "\n";
				byte[] by = str.getBytes();
				output.write(by);
			}

			for (int i = 0; i < orderList.getLast() + 1; i++) {
				String str = "Order > " + print("Order", i) + "\n";
				byte[] by = str.getBytes();
				output.write(by);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
class NotFoundNameException extends Exception {

}

class DuplicationNameException extends Exception {

}

class FullListException extends Exception {

}