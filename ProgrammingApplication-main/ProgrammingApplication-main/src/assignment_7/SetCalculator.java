package assignment_7;

import java.util.ArrayList;
import java.util.Scanner;

public class SetCalculator {

	public static void main(String[] args) {

		ArrayList<Integer> setA = new ArrayList<Integer>();
		ArrayList<Integer> setB = new ArrayList<Integer>();

		System.out.print("집합 A 입력: ");
		set(setA);

		System.out.print("집합 B 입력: ");
		set(setB);

		ArrayList<Integer> setUnion = union(setA, setB);
		System.out.print("합집합");
		print(setUnion);

		ArrayList<Integer> intersectionSet = intersection(setA, setB);
		System.out.print("교집합");
		print(intersectionSet);

		ArrayList<Integer> differenceSet = difference(setA, setB);
		System.out.print("차집합");
		print(differenceSet);

	}

	public static void set(ArrayList<Integer> set) { // 초기 집합 결정
		Scanner sc = new Scanner(System.in);
		while (true) {
			int num = sc.nextInt();
			if (num < 0)
				break;
			set.add(num);
		}
	}

	public static void print(ArrayList<Integer> al) { // 집합 출력
		System.out.print(" < ");
		for (int i = 0; i < al.size(); i++)
			System.out.print(al.get(i) + " ");
		System.out.println(">");
	}

	public static ArrayList<Integer> intersection(ArrayList<Integer> s1, ArrayList<Integer> s2) { // 교집합
		ArrayList<Integer> tmp = new ArrayList<Integer>();

		for (int i = 0; i < s2.size(); i++) {
			for (int j = 0; j < s1.size(); j++) {
				if (s1.get(j) == s2.get(i))
					tmp.add(s2.get(i));
			}
		}
		return tmp;
	}

	public static ArrayList<Integer> union(ArrayList<Integer> s1, ArrayList<Integer> s2) { // 합집합
		ArrayList<Integer> tmp = new ArrayList<Integer>(); // 집합 복사
		for (int i = 0; i < s1.size(); i++) {
			tmp.add(s1.get(i));
		}
		for (int i = 0; i < s2.size(); i++) {
			for (int j = 0; j < tmp.size(); j++) {
				if (tmp.get(0) > s2.get(i)) {
					tmp.add(0, s2.get(i));
					break;
				}
				if (tmp.get(tmp.size() - 1) < s2.get(i)) {
					tmp.add(s2.get(i));
					break;
				}
				if (tmp.get(j) < s2.get(i) && tmp.get(j + 1) > s2.get(i)) {
					tmp.add(j + 1, s2.get(i));
					break;
				}
			}
		}
		return tmp;
	}

	public static ArrayList<Integer> difference(ArrayList<Integer> s1, ArrayList<Integer> s2) {// 차집합
		ArrayList<Integer> tmp = new ArrayList<Integer>(); // 집합 복사
		for (int i = 0; i < s1.size(); i++) {
			tmp.add(s1.get(i));
		}
		for (int i = 0; i < s2.size(); i++) {
			for (int j = 0; j < tmp.size(); j++) {
				if (tmp.get(j) == s2.get(i))
					tmp.remove(j);
			}
		}
		return tmp;
	}

}
