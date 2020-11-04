package project2.ver02;

public abstract class Account {
	
	public static int cntAccount = 0;
	
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
	
	public void depositMoney(int dMoney) {
		balance += dMoney;
	}
	
	public static int getCntAccount() {

		return cntAccount;
	}

}


