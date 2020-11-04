package project2.ver02;

import project2.ver02.Account;

/*
보통예금계좌 > 최소한의 이자를 지급하는 자율 입출금식 계좌
-보통예금계좌를 의미한다.
-생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
*/
public class NormalAccount extends Account{
	public int interest;	// 기본이자율

	public NormalAccount(String accountNum, String userName, int balance, int interest) {
		super(accountNum, userName, balance);
		this.interest = interest;
	}
	
	@Override
	public String getInfo() {
		return String.format("---------------------\n"
				+ "[보통계좌]\n%s기본이자 : %d\n", super.getInfo(), interest);
	}
	
	@Override
	public void showAccInfo() {
		System.out.println(getInfo());
	}
	

	@Override
	public void depositMoney(int dMoney) {
		balance = (int)(balance + balance*interest*0.01 + dMoney);
	}
	
}
