package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPreparedStatement {
	public static void main(String[] args) {
		query();
	}

	public static void query() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/company";
			String username = "root";
			String password = "1234";

			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			
			String sql = "select * from emp where empno=? or empname=?";

			pstm = con.prepareStatement(sql);
			pstm.setInt(1, 15); // 给第一个问号赋值
			pstm.setString(2, "wanger");// 给第二个问号赋值
			rs = pstm.executeQuery();
			ResultSetMetaData md = rs.getMetaData();

			while (rs.next()) {
				for (int i = 1; i <= md.getColumnCount(); i++) {
					if (i < md.getColumnCount()) {
						System.out.print(rs.getString(i) + ",");
					} else {
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
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
