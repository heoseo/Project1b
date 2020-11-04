package project2.ver01;

public class Account {
	
	private static int cntAccount = 0;
	
	private String accountNum;	// 계좌번호(-하이픈은 없음.)
	private String name;	// 이름
	private int balance;		// 잔고
	
	public Account(String accountNum, String userName, int balance) {
		this.accountNum = accountNum;
		this.name = userName;
		this.balance = balance;
		
		cntAccount++;
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

	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public static int getCntAccount() {
		return cntAccount;
	}
	
}
