package hw_5;

import java.util.HashMap;
import java.util.Scanner;

public class BaseNDemo {

	HashMap<String, BaseNumber> symbolTable = new HashMap<>();
	Scanner sc = new Scanner(System.in);

	void printSymbolBin(int base) {
		for (String a : symbolTable.keySet()) {
			System.out.println(a + " : " + symbolTable.get(a));
		}
	}

	void doit() {
		int c = 0;
		System.out.print("진수 = ");
		int base = sc.nextInt();
		while (c <= 3) {
			c = menu(base);
		}
		printSymbolBin(base);
	}

	int menu(int base) {

		String a, b, c, op;
		int n;
		BaseNumber bn, bn2;
		System.out.printf("%n(1) a = 5 (2) a = b (3) a = b + c (기타) 종료");
		int m = sc.nextInt();
		if (m == 1) {
			System.out.printf("=> a = 5    ...");
			a = sc.next();
			op = sc.next();
			n = sc.nextInt();
			bn = new BaseNumber(n, base);
			symbolTable.put(a, bn);
			System.out.println("= " + bn);
		} else if (m == 2) {
			System.out.printf("=> a = b       ...");
			a = sc.next();
			op = sc.next();
			b = sc.next();
			bn = symbolTable.get(b);
			symbolTable.put(a, bn);
			System.out.println("= " + bn);
		} else if (m == 3) {
			System.out.print("=> a = b + c  ...");
			a = sc.next();
			op = sc.next();
			b = sc.next();
			op = sc.next();
			c = sc.next();
			bn = symbolTable.get(b);
			bn2 = symbolTable.get(c);
			if (op.equals("+")) {
				symbolTable.put(a, bn.plus(bn2));
				System.out.println(bn + " + " + bn2 + " = " + symbolTable.get(a));
			} else if (op.equals("-")) {
				symbolTable.put(a, bn.minus(bn2));
				System.out.println(bn + " - " + bn2 + " = " + symbolTable.get(a));
			}
		}
		return m;
	}
	public static void main(String[] args) {
	      BaseNDemo bn = new BaseNDemo();
	      bn.doit();
	}
}