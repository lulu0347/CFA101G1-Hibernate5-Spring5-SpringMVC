package DbUtil;

public class MySqlDbUtil {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/CFA101G1?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PASSWORD = "CFA101G1";

	public static String getDriver() {
		return DRIVER;
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}
}
