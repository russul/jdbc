package jdbc;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 该工具用来获取数据库连接
 * 
 * @author Lenovo
 * @version 1.0
 */
public class JdbcUtil {
	public static Connection getConnection() {
		// 读取配置文件里的四大连接参数
		Class clazz = null;
		try {
			clazz = Class.forName("jdbc.JdbcUtil");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		InputStream in = clazz.getClassLoader().getResourceAsStream(
				"jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String driverClassName = prop.getProperty("driverClassName");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		//加载驱动类
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//调用 DriverManager.getConnection
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
