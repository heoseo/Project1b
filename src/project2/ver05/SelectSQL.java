package project2.ver05;

import java.sql.SQLException;
import java.util.Scanner;

import project2.ver05.Account;

public class SelectSQL extends IConnectImpl{

	Account[] accountArr = new Account[50];
	Scanner sc = new Scanner(System.in);
	String findID, accountNum;
	
	public SelectSQL(Account[] accountArr) {
		super("kosmo", "1234");
		this.accountArr = accountArr;
	}
	
	public SelectSQL(Account[] accountArr, String accountNum) {
		super("kosmo", "1234");
		this.accountArr = accountArr;
		this.accountNum = accountNum;
	}
	
	@Override
	public void execute() {
		System.out.println();
		try {
			String sql = "SELECT * FROM banking_tb ";
			
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String accountNum = rs.getString(2);
				String name = rs.getString(3);
				int balance = rs.getInt(4);
				
				System.out.printf("계좌번호 : %s\n이름 : %s\n잔고 : %d\n\n", accountNum, name, balance);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	

}
