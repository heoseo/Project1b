package project2.ver05;

import java.util.Scanner;

import project2.ver05.Account;


public class InsertSQL extends IConnectImpl{
	
	Account[] accountArr = new Account[50];
	Scanner sc = new Scanner(System.in);
	
	public InsertSQL(Account[] accountArr) {
		super(ORACLE_DRIVER, "kosmo", "1234");
		this.accountArr = accountArr;
	}
	
	@Override
	public void execute() {
		
		try {
			
			System.out.println("\n****신규계좌개설****");
			
			
			
			// 1. 쿼리문준비 : 값의 세팅이 필용한 부분을 ? (인파라미터)로 대체한다.
			String query = "INSERT INTO banking_tb VALUES (seq_banking.nextval, ?, ?, ?)";
			
			// 2. preared객체 생성 : 생성시 준비한 쿼리문을 인자로 전달한다.
			psmt = con.prepareStatement(query);
			
			// 3. DB에 입력할 값을 사용자로부터 입력받음.
			System.out.print("계좌번호 : ");
			String accountNum = sc.nextLine();
			System.out.print("고객이름 : ");
			String name = sc.nextLine();
			System.out.print("잔고 : ");
			int balance = sc.nextInt();
			
			int cntAccount = Account.getCntAccount();
			accountArr[cntAccount] = new Account(accountNum, name, balance);
			System.out.println("현재 계좌 수  : " + Account.getCntAccount());
			
			System.out.println();
			// 4. 인파라미터 설정 : ? 의 순서대로 설정하고 DB이므로 인덱스는 1부터 시작
			
			psmt.setString(1, accountNum);
			psmt.setString(2, name);
			psmt.setInt(3, balance);

			
			// 5.쿼리실행을  위해 prepared객체를 실행한다.
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}


}
