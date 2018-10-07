package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Demo {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		// Class.forName("com.mysql.jdbc.Driver");
		// Connection
		// con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","1234");
		// System.out.println(con);
		query();
	}

	public static void query() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/company";
			String username = "root";
			String password = "1234";

			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery("select *from emp where empno='15'");
			ResultSetMetaData md = rs.getMetaData();

			while (rs.next()) {
				for (int i = 1; i <= md.getColumnCount(); i++) {
					if (i < md.getColumnCount()) {
						System.out.print(rs.getString(i) + ",");
					}else{
						System.out.print(rs.getString(i));
					}
					
				}
				System.out.println();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)// 防止空指针异常
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
