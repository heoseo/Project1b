package project2.ver06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import project2.BankingSystemVer06;
import project2.ver06.Account;

public class AccountManager{
	
	public static int saveOption = 2;	// 자동저장 처음엔 Off(2) on은 (1)	
	public static Scanner sc = new Scanner(System.in);
	public HashSet<Object> accountSet;
	
	AutoSaverT thread;
	
	
	public AccountManager(int num) {
		accountSet = new HashSet<Object>();
		
		 
		try {
			ObjectInputStream in =
				new ObjectInputStream(
					new FileInputStream("src/project2/AccountInfo.obj")
				);
			
			while(true) {
				Account account= (Account)in.readObject();
				//더이상 복원할 객체가 없다면 루프를 탈출한다.
				if(account==null) break;
				accountSet.add(account);
			}
		}
		catch (Exception e) {
//			System.out.println("복원시 예외발생");
		}
	}
	

	// 1
	public void makeAccount() {
		
		int makeAccountMenu = 0;
		
		System.out.println("------계좌선택------");
		System.out.print("1. 보통계좌\n2. 신용신뢰계좌\n");
		
		while(true) {	
			// Point A
			
			try {
				makeAccountMenu = BankingSystemVer06.readMenu(2);
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
			
			
			// 1. 보통계좌개설
			if(makeAccountMenu==1) {
				Account newAccount = 
						new NormalAccount(accountNum, name, balance, interest);
				
				// 중복검사. 
				if(!accountSet.add(newAccount)) {
					// true면 덮어쓰기 
					if(accountOverrite(newAccount)) break;
					else {
						continue;	// fale면 덮어쓰지 않음. => Point A
					}
				}
				else {
				}
				
			}// 2. 신용신뢰계좌 개설
			else if(makeAccountMenu==2){
				String grade;
				while(true) {
					System.out.print("신용등급(A,B,C등급) : ");
					grade = sc.nextLine();
					if(!grade.equals("A") && !grade.equals("B") && !grade.equals("C")
							&&!grade.equals("a")&&!grade.equals("b")&&!grade.equals("c")) {
						System.out.println(">>A, B, C 중에서 입력하세요\n");
						continue;
					}
					break;
				}
				
				Account newAccount = new HighCreditAccount(accountNum, name, balance, interest, grade);
				
				// 중복검사. 
				if(!accountSet.add(newAccount)) {
					// true면 덮어쓰기 
					if(accountOverrite(newAccount)) break;
					else	continue;	// fale면 덮어쓰지 않음. => Point A
				}
				else {
				}
			}
			
			// 여기까지오면 계좌개설 완료.
			break;
		}
		
		// 현재 생성된 계좌 수 출력.
		System.out.println(">>현재 계좌 수  : " + accountSet.size() + "\n");
		System.out.println();
	}
	
	
	// 계좌생성-덮어쓰기 여부 판단.
	public boolean accountOverrite(Account newAccount) {
		System.out.print(">>이미 있는 계좌번호 입니다. 덮어쓸까요? (Y/N) ");
		String a = sc.nextLine();
		
		if(a.equals("y") || a.equals("Y"))
		{
			System.out.println("Y!!!!!");
			accountSet.remove(newAccount);
			accountSet.add(newAccount);
			System.out.println(">>덮어쓰기 완료\n");
			
			return true;
		}
		else if( a.equals("n") || a.equals("N"))
		{
			System.out.println();
			return false;
		}
		else{
			System.out.println("y와 n중에서 입력하세요.");
			accountOverrite(newAccount);
		}
		return false;
	}
	
	
	
	// 2 입금
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
		
		// isExists()로 계좌번호 찾은 후 입금처리
		Account findAccount = isExists(accountNum);
		if(findAccount==null)
		{
			System.out.println(">>일치하는 계좌번호가 없습니다.\n");
		
		}
		else {
			findAccount.depositMoney(dMoney);
			System.out.println(dMoney);
			System.out.println(">>입금이 완료되었습니다.\n");
		}
		
	}
	
	// 3 출금
	public void withdrawMoney() {
		sc = new Scanner(System.in);
		
		System.out.println("출금할 계좌번호와 금액을 입력하세요.");
		System.out.print("계좌번호 ");
		String accountNum = sc.nextLine();
		
		// 출금액 입력
		int wMoney;
		System.out.print("출 금 액 >>");		
		wMoney = readMoney(2);	// readMoney()에서 적절한 금액인지 검사후 금액 반환.
			
		
		Account findAccount = isExists(accountNum);
		
		// 일치하는 계좌가 있는지 검사.
		if(findAccount == null) {
			System.out.println(">>일치하는 계좌번호가 없습니다.\n");
		}
		else
			findAccount.withdrawMoney(wMoney);
		
	}
	
	
	// 입금, 출금에서 계좌번호가 존재하는지 검사.
	public Account isExists(String accountNum) {
		Account account;
		Iterator<Object> itr = accountSet.iterator();
		while(itr.hasNext()) {
			account = (Account) itr.next();
			if(accountNum.equals(account.accountNum)) {
				// toString()을 오버라이딩했으므로 객체 출력이 가능.
				return account;
			}
			// 포함한게 안걸리면 flag는 false임.
		}
		
		// 여기까지오면 일치하는 계좌가 없는것이므로 null 반환.
		return null;
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
			System.out.println(500*choice + "원 단위로 입력해주세요.\n");	
			// 입금이면 500, 출금이면 1000
			return readMoney(choice);
		}

		return money;
	}
	
	// 4 전체 계좌정보 출력.
	public void showAccInfo() {
		
		Iterator itr = accountSet.iterator();
		while(itr.hasNext()) {
			Account find = (Account) itr.next();
			find.showAccInfo();
		}
		
		System.out.println("==전체정보가 출력되었습니다==\n");
		System.out.println();
	}////end of showAccInfo
	
	// 5 자동저장 옵션
	public void saveOption() {

		
		System.out.println("1. 자동저장 On\n2. 자동저장 Off\n");
		try {
			int optionMenu = BankingSystemVer06.readMenu(2);
			
			
			// 자동저장옵션 그대로.
			if(optionMenu == saveOption) {	// 처음엔 OFF로 초기화된 상태.
				System.out.print("이미 자동저장 " + ((optionMenu==1)?"On":"Off") + " 상태입니다.\n\n");
			}
			else {
				// 자동저장옵션 바뀜.
				saveOption = optionMenu;
				System.out.println("자동저장 " + ((optionMenu==1)?"On":"Off") + "\n\n");
				
				
				if(saveOption == 1) {	// 자동저장 ON
					thread = new AutoSaverT(accountSet);	// 쓰레드 생성
					thread.setDaemon(true);	// 독립쓰레드를 데몬쓰레드로 만들어준다.
					thread.start();
				}
				else if (saveOption == 2) {
					thread.interrupt();	// 자동저장 OFF
				}
			}
		}
		catch(MenuSelectException e) {
			System.out.println(e.getMessage()+"\n");
			saveOption();
		}
		catch(Exception e) {}
		
		
		
	}
	
	// 6. 3by3게임
	
	// 7. 프로그램 종료 후 obj파일로 저장.
	public void saveToObject() {
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("src/project2/AccountInfo.obj"));

			Iterator itr = accountSet.iterator();
			while(itr.hasNext()) {
			Account find = (Account)itr.next();
			out.writeObject(find);
			}
			
			System.out.println(">>obj파일이 저장되었습니다.");
		}
		catch(Exception e) {
		}
	}
	
}
