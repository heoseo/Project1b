package project2;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2.ver04.AccountManager;
import project2.ver04.MenuChoice;
import project2.ver04.MenuSelectException;

public class BankingSystemVer04 implements MenuChoice{
	
	
	
	public static void main(String[] args) {
		
		AccountManager acmg = new AccountManager(50);
		
		while(true) {
			System.out.println(
					"=================================Menu================================\n"
							+ "  1.계좌개설  2.입금  3.출금  4.전체계좌정보출력  5.저장옵션  6.프로그램종료\n"
							+   "=====================================================================");
				
			int menu=0;
			try {
				menu = readMenu(1);
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage()+"\n");
				continue;
			}
			
			switch(menu) {
			case MenuChoice.MAKE:
				System.out.println("\n****신규계좌개설****\n");
				acmg.makeAccount();
				break;
			case MenuChoice.DEPOSIT:
				System.out.println("\n****입금****\n");
				if(acmg.accountSet.size() == 0)
				{
					System.out.println(">>계좌가 없습니다. 생성후 시도해주세요.\n");
					break;
				}
				acmg.depositMoney();
				break;
			case WITHDRAW:
				System.out.println("\n****출금****\n");
				if(acmg.accountSet.size() == 0)
				{
					System.out.println(">>계좌가 없습니다. 생성후 시도해주세요.\n");
					break;
				}
				acmg.withdrawMoney();
				break;
			case INQUIRE:
				System.out.println("\n***전체계좌출력***\n");
				if(acmg.accountSet.size() == 0)
				{
					System.out.println(">>저장된 정보가 없습니다.\n");
					break;
				}
				acmg.showAccInfo();
				break;
			case SAVEOPTION:
				System.out.println("\n***저장 옵션***\n");
				acmg.saveOption();
				break;
			case EXIT:
				System.out.println("\n=================================="
						+ "\n\t프로그램이 종료되었습니다.\n"
						+ "==================================");
				acmg.saveToObject();
				System.exit(0);
			}
		}
	}

	public static int readMenu(int a) throws MenuSelectException {
		System.out.print(">>선택 : ");
		Scanner sc = new Scanner(System.in);
		int inputMenu=0;
		try {
			inputMenu = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println(">>정수만 입력하세요\n");
		}
		
		//개발자가 예외를 감지하여...
		// 1. 메인메뉴 예외 감지
		if(a == 1) {
			if(inputMenu<1 || inputMenu > 6) {
				//예외객체를 생성한 후 프로그램으로 throw 한다.
				MenuSelectException ex = new MenuSelectException(">>1부터 6까지의 정수만 입력하세요.\n");
				throw ex;
			}
		}
		// 2. 서브메뉴 예외 감지
		else if(a == 2) {
			if(inputMenu != 1 && inputMenu != 2) {
				//예외객체를 생성한 후 프로그램으로 throw 한다.
				MenuSelectException ex = new MenuSelectException("1과 2만 입력하세요.\n");
				throw ex;
			}			
		}
		
		return inputMenu;
	}
	
}
