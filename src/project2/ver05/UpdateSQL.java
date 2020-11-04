package project2.ver05;

import java.sql.SQLException;
import java.util.Scanner;

import project2.ver05.Account;

public class UpdateSQL extends IConnectImpl{

	static Account[] accountArr = new Account[50];
	Scanner sc = new Scanner(System.in);
	int choice;
	String str;
	
	// 생성자에서 DB연결
	public UpdateSQL(Account[] accountArr, int choice) {
		super("kosmo", "1234");
		this.accountArr = accountArr;
		this.choice = choice;
		
		str = (choice == 1)? "입금" : "출금";
	}
	

	@Override
	public void execute() {

		String sql = "UPDATE banking_tb "
				+ " SET balance= balance";
				
		
		
		System.out.println("\n****" + str + "****");
		System.out.println(str + "할 계좌번호와 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String accountNum = sc.nextLine();
		System.out.print(str + "액 : ");
		int money = sc.nextInt();
		
		
		
		if(choice == 1) {	// 입금
			sql = sql.concat(" + ? WHERE accountnum = ?");
					// update banking_tb
					// set balance = balance + ? ;
					// where accountnum = ?
		}
		else if (choice == 2) {	// 출금
			sql = sql.concat(" - ? WHERE accountnum = ?");
					// update banking_tb
					// set balance = balance - ? 
					// where accountnum = ?
		}
		System.out.println(str + "이 완료되었습니다.\n");
		
		
		try {
			
			psmt = con.prepareStatement(sql);
				// 인파라미터 설정시 해당 인덱슴나 맞으면 순서는 상관없음.
				psmt.setInt(1, money);
				psmt.setString(2, accountNum);
				
				int affected = psmt.executeUpdate();
				System.out.println(affected + "행이 업데이트 되었습니다.");
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}
	
	
	private static int isExists(String accountNum) {
		int index = 100000;	// 계좌번호 일치하는게 없으면 100000 리턴
		
		for(int i = 0; i < Account.getCntAccount(); i++) {
			if(accountNum.equals(accountArr[i].getAccountNum()))
				index = i;
		}
		
		return index;
	}
	

}
