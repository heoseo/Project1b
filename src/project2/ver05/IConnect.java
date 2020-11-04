package project2.ver05;

public interface IConnect {
	
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin://@localhost:1521:orcl";
	
	void connect(String user, String pass);
	void execute();
	void close();
	
	// 사용자 입력을 위한 추상메소드 선언
	String scanValue(String title);
	
}
