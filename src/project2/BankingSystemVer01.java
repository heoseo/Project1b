package project2;

import java.util.Scanner;

import project2.ver01.Account;
import project2.ver01.MenuChoice;

public class BankingSystemVer01 implements MenuChoice {
	
	static Scanner sc = new Scanner(System.in);

	static Account[] accountArr = new Account[50];
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println(
					"-----Menu-----\n"
					+ "1. 계좌계설\n"
					+ "2. 입급\n"
					+ "3. 출금\n"
					+ "4. 전체계좌정보출력\n"
					+ "5. 프로그램종료");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case MAKE:
				makeAccount();
				break;
			case DEPOSIT:
				depositMoney();
				break;
			case WITHDRAW:
				withdrawMoney();
				break;
			case INQUIRE:
				showAccInfo();
				break;
			case EXIT:
				System.out.println("\n=================================="
						+ "\n\t프로그램이 종료되었습니다.\n"
						+ "==================================");
				System.exit(0);
				break;
			}
		}
		
	}
	
	
	private static void makeAccount() {
		System.out.println("\n****신규계좌개설****");
		sc = new Scanner(System.in);
		System.out.print("계좌번호 : ");
		String accountNum = sc.nextLine();
		System.out.print("고객이름 : ");
		String name = sc.nextLine();
		System.out.print("잔고 : ");
		int balance = sc.nextInt();
		
		int cntAccount = Account.getCntAccount();
		accountArr[cntAccount] = new Account(accountNum, name, balance);
		System.out.println("현재 계좌 수  : " + Account.getCntAccount());
		
		System.out.println();
	}
	
	private static void depositMoney() {
		sc = new Scanner(System.in);
		
		System.out.println("\n****입      금****");
		System.out.println("입금할 계좌번호와 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String accountNum = sc.nextLine();
		System.out.print("입 금 액 : ");
		int dMoney = sc.nextInt();
		System.out.println("계좌번호:" + accountNum + " 입금액 : " + dMoney);
		
		int userIndex = isExists(accountNum); 
		if(userIndex != 100000) {	// true : 계좌번호 존재함
			int balance = accountArr[userIndex].getBalance();
			accountArr[userIndex].setBalance(balance+dMoney);
			System.out.println("입금이 완료되었습니다.");
		}
		else if(userIndex == 100000) {
			System.out.println("일치하는 계좌번호가 없습니다.");
		}
		
		System.out.println();
	}
	
	private static void withdrawMoney() {
		sc = new Scanner(System.in);
		System.out.println("\n****출      금****");
		
		System.out.println("출금할 계좌번호와 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String accountNum = sc.nextLine();
		System.out.print("출 금 액 : ");
		int wMoney = sc.nextInt();
		
		int userIndex = isExists(accountNum); 
		if(userIndex != 100000) {	// true : 계좌번호 존재함
			int balance = accountArr[userIndex].getBalance();
			accountArr[userIndex].setBalance(balance-wMoney);
			System.out.println("출금이 완료되었습니다.");
		}
		else if(userIndex == 100000) {
			System.out.println("일치하는 계좌번호가 없습니다.");
		}
	}
	
	private static void showAccInfo() {
		
		System.out.println("\n****계좌정보출력****");
		for(int i = 0; i <Account.getCntAccount(); i++) {
			System.out.println("---------------------");
			System.out.println("계좌번호 : " + accountArr[i].getAccountNum());
			System.out.println("고객이름 : " + accountArr[i].getName());
			System.out.println("잔      고 : " + accountArr[i].getBalance());
		}
		System.out.println();
	}
	
	private static int isExists(String accountNum) {
		int index = 100000;	// 계좌번호 일치하는게 없으면 100000 리턴
		
		for(int i = 0; i < Account.getCntAccount(); i++) {
			if(accountNum.equals(accountArr[i].getAccountNum()))
				index = i;
		}
		
		return index;
	}

}
