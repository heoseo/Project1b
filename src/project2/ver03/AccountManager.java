package project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2.BankingSystemVer04;
import project2.ver04.MenuSelectException;


public class AccountManager {
	
	static final int MAKE 		= 1;
	static final int DEPOSIT 	= 2;
	static final int WITHDRAW	= 3;
	static final int INQUIRE	= 4;
	static final int EXIT 		= 5;
	
	private static Scanner sc = new Scanner(System.in);

	public BankingSystemVer04 basv4 = new BankingSystemVer04();
	private static Account[] accountArr;
	
	public AccountManager(int num) {
		accountArr = new Account[num];
//		sc = new Scanner(System.in);
		
	}

	

	public void makeAccount() {
		
		int makeAccountMenu = 0;
		
		System.out.println("------계좌선택------");
		System.out.print("1. 보통계좌\n2. 신용신뢰계좌\n");
		
		
		while(true) {	
			// Point A
			
			try {
				makeAccountMenu = basv4.readMenu(2);
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage()+"\n");
				continue;
			}
			catch(Exception e) {}
			
			System.out.println((makeAccountMenu==1)?"\n보통계좌개설]":"\n신용신뢰계좌개설]");
		
			// 공통사항 입력
			sc = new Scanner(System.in);
			System.out.print("계좌번호 : ");
			String accountNum = sc.nextLine();
			System.out.print("고객이름 : ");
			String name = sc.nextLine();
			System.out.print("잔고 : ");
			int balance = sc.nextInt();sc.nextLine();
			System.out.print("기본이자%(정수형태로입력) : ");
			int interest = sc.nextInt();sc.nextLine();
			
			// 잔고검사
			if(balance < 0) {
				System.out.println(">>잔고는 0원 이상으로 입력하세요.\n");
				continue;
			}
			
			String grade;
			int index=Account.getCntAccount();
				
			if(makeAccountMenu==1) {
				accountArr[index] = new NormalAccount(accountNum, name, balance, interest);
			}
			else if(makeAccountMenu==2){
				System.out.print("신용등급(A,B,C등급) : ");
				grade = sc.nextLine();
				
				accountArr[index] = new HighCreditAccount(accountNum, name, balance, interest, grade);
			}
				
			
			break;
		}
		
		System.out.println(">>현재 계좌 수  : " + Account.getCntAccount() + "\n");
		System.out.println();
	}
	
	
	/*
	 * 음수를 입금 할 수 없다.
		금액 입력시 문자를 입력할 수 없다.
		입금액은 500원단위로 가능하다. Ex) 1000, 1500원 입금가능, 1600원 입금불가.

	 */
	public void depositMoney() {
		
		sc = new Scanner(System.in);
		System.out.println("입금할 계좌번호와 금액을 입력하세요.");
		
		// 계좌번호 입력
		System.out.print("계좌번호 >>");
		String accountNum = sc.nextLine();

		// 입금액(dMoney) 입력
		int dMoney=0;
			
		System.out.print("입 금 액 ");
		dMoney = readMoney(1);	// readMoney()에서 적절한 금액 입력.
		
		// 입금처리
		int userIndex = isExists(accountNum);
		if(userIndex != 100000) {	// true : 계좌번호 존재함
			accountArr[userIndex].depositMoney(dMoney);
			System.out.println(">>입금이 완료되었습니다.\n");
		}
		else if(userIndex == 100000) {
			System.out.println(">>일치하는 계좌번호가 없습니다.\n");
		}
		
		System.out.println();
	}
/*	
	출금 : 
		-음수를 출금 할 수 없다.
		-잔고보다 많은 금액을 출금요청할 경우 아래와 같이 처리한다.
		잔고가 부족합니다. 금액전체를 출금할까요?
		YES : 금액전체 출금처리
		NO : 출금요청취소
		-출금은 1000원 단위로만 출금이 가능하다. Ex)2000원 출금가능, 1100원을 출금불가
*/
	public void withdrawMoney() {
	sc = new Scanner(System.in);
		
		System.out.println("출금할 계좌번호와 금액을 입력하세요.");
		System.out.print("계좌번호 ");
		String accountNum = sc.nextLine();
		
		// 출금액 입력
		int wMoney;
		System.out.print("출 금 액 >>");		
		wMoney = readMoney(2);	// readMoney()에서 적절한 금액인지 검사후 금액 반환.
			
		
		int userIndex = isExists(accountNum); 
		
		if(userIndex != 100000) {
			accountArr[userIndex].withdrawMoney(wMoney);
		}
		else {
			System.out.println(">>일치하는 계좌번호가 없습니다.\n");
		}
		
	}
	
	// 입금, 출금에서 입력한 금액이 적절할때까지 메소드 반복.
	public int readMoney(int choice) {
		// choice가 1이면 입금, choice가 2이면 출금\
		System.out.print(">>");
		Scanner sc = new Scanner(System.in);
		int money=0;
		try {
			money = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e) {	// money가 정수인지 검사.
			System.out.println("정수를 입력하세요.");
			return readMoney(choice);
		}
		
		// money가 음수인지 검사
		if(money<0) {
			System.out.println("음수를 입력할 수 없습니다.\n");
			return readMoney(choice);
		}
		
		// money가 500/100(입금/출금)인지 검사.	
		if(money%500*choice != 0) {
			System.out.println(500*choice + "원 단위로 입력해주세요.\n");	// 입금이면 500, 출금이면 1000
			return readMoney(choice);
		}

		return money;
	}
		
	
	public void showAccInfo() {
		for(int i=0 ; i<Account.getCntAccount(); i++) {
			accountArr[i].showAccInfo();
		}

		System.out.println("==전체정보가 출력되었습니다==\n");
		System.out.println();
	}////end of showAccInfo
	
	
	public int isExists(String accountNum) {
		int index = 100000;	// 계좌번호 일치하는게 없으면 100000 리턴
		
		for(int i = 0; i < Account.getCntAccount(); i++) {
			if(accountNum.equals(accountArr[i].accountNum))
				index = i;
		}
		
		return index;
	}
	
	
	
	
}
