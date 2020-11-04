package project2.ver03;

import project2.ver03.Account;
import project2.ver03.CustomSpecialRate;

/*
신용신뢰계좌 > 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
-NormalAccount 와 마찬가지로 생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
-고객의 신용등급을 A, B, C로 나누고 계좌개설시 이 정보를 등록한다.
-A,B,C 등급별로 각각 기본이율에 7%, 4%, 2%의 이율을 추가로 제공한다.
*/
public class HighCreditAccount extends Account{
	public int interest;	// 기본이자율
	public String grade;	// 신용등급
	CustomSpecialRate csr;
	
	public HighCreditAccount(String accountNum, String userName, int balance, int interest, String grade) {
		super(accountNum, userName, balance);
		this.interest = interest;
		this.grade = grade;
		
		// grade abc값 검사는 생략
		csr = new CustomSpecialRate(grade);
		
	}
	
	@Override
	public String getInfo() {
		return String.format("---------------------\n[신용신뢰계좌]"
				+ "\n%s기본이자 : %d\n신용등급 : %s\n",  super.getInfo(), interest, grade);
	}
	
	@Override
	public void showAccInfo() {
		System.out.println(getInfo());
	}
	
	
	@Override
	public void depositMoney(int dMoney) {
		balance = (int)(balance + balance*(interest+csr.plusInterest)*0.01 + dMoney);
	}
	
}
