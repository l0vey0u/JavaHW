package hw_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BaseNumber {
	   void convert(int n, int base){
	      ArrayList<Integer> result =new ArrayList<>();
	      int print_n=n;
	      if(n==0) {
	         result.add(0);
	         return;
	      }
	      while(n>0) {
	         result.add(n%base);
	         n=n/base;
	      }
	      Collections.reverse(result);
	      for(int a:result) {
	         System.out.print(a+"_");
	      }
	      System.out.printf("(%d)  =  %d%n",base,print_n);
	   }
	      
	   HashMap<String, Integer> symbolTable=new HashMap<>();
	   
	   void printSymbolBin(int base) {
	      for(String a : symbolTable.keySet()) {
	         System.out.print(a+" : \t");
	         convert(symbolTable.get(a), base);
	      }
	   }
	      
	   void doit() {
	      int c=0;
	      System.out.print("진수 = ");
	      int base=sc.nextInt();
	      while(c<=3) {
	         c=menu();
	      }
	      printSymbolBin(base);
	   }
	   int menu() {
	      
	      String a, b, c, op;
	      int n;
	      System.out.printf("(1) a = 5 (2) a = b (3) a = b + c (기타) 종료");
	      int m=sc.nextInt();
	      if(m==1) {
	         System.out.printf("=> a = 5    ...");
	         a=sc.next();
	         op=sc.next();
	         n=sc.nextInt();
	         symbolTable.put(a,n);         
	      }
	      else if(m==2) {
	         System.out.printf("=> a = b       ...");
	         a=sc.next();
	         op=sc.next();
	         b=sc.next();
	         symbolTable.put(a,symbolTable.get(b));
	      }
	      else if (m == 3) {
	         System.out.print("=> a = b + c  ...");
	         a=sc.next();
	         op=sc.next();
	         b=sc.next();
	         op=sc.next();
	         c=sc.next();
	         if(op.equals("+")) {
	            symbolTable.put(a,symbolTable.get(b)+symbolTable.get(c));
	         }
	         else if(op.equals("-")) {
	            symbolTable.put(a,symbolTable.get(b)-symbolTable.get(c));
	         }
	      }
	      return m;      
	   }
	   Scanner sc=new Scanner(System.in);
	   public static void main(String[] args) {
	      BaseNumber bn = new BaseNumber();
	      bn.doit();
	   }
	}