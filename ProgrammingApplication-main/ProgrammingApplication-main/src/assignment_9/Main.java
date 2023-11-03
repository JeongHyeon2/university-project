package assignment_9;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		AddressBook adb = new AddressBook();

		while (true) {
			System.out.print("1. Input Person, 2. Delete Person, 3. Print All, 4. Save File, 5. Exit ? ");
			try {
				switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.print("Input Person ?");
					String[] data = sc.nextLine().split(",");
					adb.input(new Person(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
					System.out.println("> OK");
					break;
				case 2:
					System.out.print("Input id ?");
					adb.delete(Integer.parseInt(sc.nextLine()));
					System.out.println("> OK");
					break;
				case 3:
					System.out.print(adb.printAll());
					break;
				case 4:
					System.out.print("File name? ");
					adb.saveFile(sc.nextLine());
					System.out.println("완료");
					break;
				case 5:
					System.out.println("**종료**");
					return;
				default:
					System.out.println("잘못된 입력입니다");
				}
			} catch (NotFoundIdException e) {
				System.out.println("[Error] id를 찾을 수 없습니다.");
			} catch (IdDulicationException e) {
				System.out.println("[Error] id가 중복되었습니다.");
			} catch (Exception e) {
				System.out.println("[Error] 잘못된 입력입니다.");
			}
		}

	}

}
