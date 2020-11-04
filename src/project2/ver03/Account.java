package project2.ver03;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Account implements Serializable{
	
	private static int cntAccount = 0;
	
	public String accountNum;	// 계좌번호(-하이픈은 없음.)
	public String name;	// 이름
	public int balance;		// 잔고
	
	public Account(String accountNum, String userName, int balance) {
		this.accountNum = accountNum;
		this.name = userName;
		this.balance = balance;
		
		cntAccount++;
	}
	
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	// 계좌정보 문자열 get메소드
	public String getInfo() {
		return String.format("계좌 : %s\n고객이름 : %s\n잔고:%d\n",
				accountNum,name, balance);
	}

	
	// 겍체의 정보 출력용 메소드
	public void showAccInfo() {
		System.out.println(getInfo());
	}

	// 입금 메소드
	public void depositMoney(int dMoney) {
		balance += dMoney;
	}
	
	// 출금 메소드
	public void withdrawMoney(int wMoney) {
				
		// 잔고가 0원인 경우.
		if(balance == 0) {
			System.out.println(">>잔액이 0원입니다. 출금을 취소합니다.\n");
			return;
		}
		
		//잔고에서 출금 가능한지 검사.
		if(wMoney <= balance) {
			// 1. 출금 가능함.
			balance=(balance-wMoney);
			System.out.println(">>출금이 완료되었습니다.\n");
		}
		else{// 2. 출금 불가능.
			System.out.println(">>잔고가 부족합니다. 금액전체를 출금할까요?\n" + 
					"1 : 금액전체 출금처리\n" + 
					"2 : 출금요청취소");
			
			int withdrawMenu;
			while(true) {
				try{
					System.out.print(">>선택 : ");
					Scanner sc = new Scanner(System.in);
					withdrawMenu = sc.nextInt();
					
					// 잔고 전체 출금.
					if(withdrawMenu ==1)
					{
						balance=0;
						return;
					}
					else if(withdrawMenu == 2) {
						return;	// 출금요청 취소
					}
					else {
						System.out.println(">>1과2중에 입력하세요.\n");
						continue;
					}
				}
				catch(Exception e) {
					System.out.println(">>정수를 입력하세요.(1또는 2)\n");
				}
			}
		}
		
	}

	public static int getCntAccount() {

		return cntAccount;
	}


		
}
