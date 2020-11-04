package project2.ver05;

public class DeleteSQL extends IConnectImpl{

	public DeleteSQL() {
		super("kosmo", "1234");
	}
	
	@Override
	public void execute() {

		try {
			// 1. 쿼리문 준비
			String query = "DELETE FROM member WHERE id = ?";
			// 2. prepared 객체 생성
			psmt = con.prepareStatement(query);
			// 3. 사용자로부터 입력받은 값을 인파라미터로 설정
			psmt.setString(1, scanValue("삭제할 아이디"));
			// 4. 쿼리 실행
			System.out.println(psmt.executeUpdate() + "행이 삭제되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {

		new DeleteSQL().execute();
		
	}

}
