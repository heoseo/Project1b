package project2.ver02;

import java.util.Scanner;

public class AccountManager {
	
	private static Scanner sc = new Scanner(System.in);

	private static Account[] accountArr;
	
	public AccountManager() {
		accountArr = new Account[50];
//		sc = new Scanner(System.in);
		
	}

	
	public void makeAccount() {
		System.out.println("\n****신규계좌개설****");
		System.out.println("------계좌선택------");
		
		System.out.print("1. 보통계좌\n2. 신용신뢰계좌\n선택 :");
		int makeMenu = Integer.parseInt(sc.nextLine());
		
		if(makeMenu == 1 || makeMenu==2) {
			
			// 공통사항 입력
			sc = new Scanner(System.in);
			System.out.print("계좌번호 : ");
			String accountNum = sc.nextLine();
			System.out.print("고객이름 : ");
			String name = sc.nextLine();
			System.out.print("잔고 : ");
			int balance = Integer.parseInt(sc.nextLine());
			
			String grade;
			int index=Account.cntAccount;
			
			
			System.out.print("기본이자%(정수형태로입력) : ");
			int interest = Integer.parseInt(sc.nextLine());
			if(makeMenu==1) {
				accountArr[index] = new NormalAccount(accountNum, name, balance, interest);
			}
			else if(makeMenu==2){
				System.out.print("신용등급(A,B,C등급) : ");
				grade = sc.nextLine();
				
				accountArr[index] = new HighCreditAccount(accountNum, name, balance, interest, grade);
			}
		}
		else {
			System.out.println("[숫자를 잘못입력했습니다!!]  1또는 2를 입력하세요\n");
			return;
		}
		
		System.out.println("계좌개설이 완료되었습니다.");
		System.out.println("현재 계좌 수  : " + Account.cntAccount);
		System.out.println();
	}
	
	public void depositMoney() {
		System.out.println("\n****입      금****");
		sc = new Scanner(System.in);
		System.out.println("입금할 계좌번호와 금액을 입력하세요.");
		
		System.out.print("계좌번호 : ");
		String accountNum = sc.nextLine();

		System.out.print("입 금 액 : ");
		int dMoney = Integer.parseInt(sc.nextLine());
		
		int userIndex = isExists(accountNum); 
		if(userIndex != 100000) {	// true : 계좌번호 존재함
			int balance = accountArr[userIndex].balance;
			accountArr[userIndex].depositMoney(dMoney);
			System.out.println("입금이 완료되었습니다.");
		}
		else if(userIndex == 100000) {
			System.out.println("일치하는 계좌번호가 없습니다.");
		}
		
		System.out.println();
	}
	
	public void withdrawMoney() {
		sc = new Scanner(System.in);
		System.out.println("\n****출      금****");
		
		System.out.println("출금할 계좌번호와 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String accountNum = sc.nextLine();
		System.out.print("입 금 액 : ");
		int wMoney = Integer.parseInt(sc.nextLine());
		
		int userIndex = isExists(accountNum); 
		if(userIndex != 100000) {	// true : 계좌번호 존재함
			int balance = accountArr[userIndex].balance;
			accountArr[userIndex].balance=(balance-wMoney);
			System.out.println("출금이 완료되었습니다.");
		}
		else if(userIndex == 100000) {
			System.out.println("일치하는 계좌번호가 없습니다.");
		}
	}
	
	public void showAccInfo() {
		for(int i=0 ; i<Account.cntAccount; i++) {
			accountArr[i].showAccInfo();
		}

		System.out.println("==전체정보가 출력되었습니다==");
		System.out.println();
	}////end of showAccInfo
	
	
	public int isExists(String accountNum) {
		int index = 100000;	// 계좌번호 일치하는게 없으면 100000 리턴
		
		for(int i = 0; i < Account.cntAccount; i++) {
			if(accountNum.equals(accountArr[i].cntAccount))
				index = i;
		}
		
		return index;
	}
}
