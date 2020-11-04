package project2;

import java.util.Scanner;

import project2.ver02.AccountManager;
import project2.ver02.Account;
import project2.ver02.MenuChoice;

public class BankingSystemVer02  implements MenuChoice{
	
	public static void main(String[] args) {
		
		AccountManager acmg = new AccountManager();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println(
					"=================================Menu================================\n"
							+ "  1.계좌개설  2.입금  3.출금  4.전체계좌정보출력  5.프로그램종료\n"
							+   "=====================================================================");
				
			int menu= sc.nextInt();
			
			if(menu < 1 || menu > 5) {
				
				System.out.println("1부터 5까지의 정수만 입력하세요.");
				continue;
			}
			
			switch(menu) {
			case MenuChoice.MAKE:
				System.out.println("\n****신규계좌개설****\n");
				acmg.makeAccount();
				break;
			case MenuChoice.DEPOSIT:
				System.out.println("\n****입금****\n");
				if(Account.getCntAccount() == 0)
				{
					System.out.println(">>계좌가 없습니다. 생성후 시도해주세요.\n");
					break;
				}
				acmg.depositMoney();
				break;
			case WITHDRAW:
				System.out.println("\n****출금****\n");
				if(Account.getCntAccount() == 0)
				{
					System.out.println(">>계좌가 없습니다. 생성후 시도해주세요.\n");
					break;
				}
				acmg.withdrawMoney();
				break;
			case INQUIRE:
				System.out.println("\n***전체계좌출력***\n");
				if(Account.getCntAccount() == 0)
				{
					System.out.println(">>저장된 정보가 없습니다.\n");
					break;
				}
				acmg.showAccInfo();
				break;
			case EXIT:
				System.out.println("\n=================================="
						+ "\n\t프로그램이 종료되었습니다.\n"
						+ "==================================");
				System.exit(0);
			}
		}
		
	}
	
}
