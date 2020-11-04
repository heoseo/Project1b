package project2.ver05;

public class Account {
	
	private static int cntAccount = 0;
	
	private String accountNum;	// 계좌번호(-하이픈은 없음.)
	private String name;	// 이름
	private int balance;		// 잔고
	
	public Account(String accountNum, String userName, int balance) {
		super();
		this.accountNum = accountNum;
		this.name = userName;
		this.balance = balance;
		
		cntAccount++;
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
