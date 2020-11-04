package project2;

import java.util.Scanner;

import project2.ver05.Account;
import project2.ver05.MenuChoice;
import project2.ver05.InsertSQL;
import project2.ver05.SelectSQL;
import project2.ver05.UpdateSQL;


public class BankingSystemVer05 implements MenuChoice {
	
	static final int MAKE 		= 1;
	static final int DEPOSIT 	= 2;
	static final int WITHDRAW	= 3;
	static final int INQUIRE	= 4;
	static final int EXIT 		= 5;
	
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
				new InsertSQL(accountArr).execute();
				break;
			case DEPOSIT:
				new UpdateSQL(accountArr, 1).execute();
				break;
			case WITHDRAW:
				new UpdateSQL(accountArr, 2).execute();
				break;
			case INQUIRE:
				new SelectSQL(accountArr).execute();
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
