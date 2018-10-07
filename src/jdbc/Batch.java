package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 
 * 批处理
 */
public class Batch {
	public static void main(String[] args) {
		insert();
	}

	public static void insert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/company";
//			String url = "jdbc:mysql://localhost:3306/company?rewriteBatchedStatements=true";
			String username = "root";
			String password = "1234";

			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			String sql = "insert into employee values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			
			// 将一批sql语句装进集合
			for (int i = 1; i <= 100000; i++) {
				pstmt.setInt(2, i);
				pstmt.setString(1, "stu_" + i);
				pstmt.setString(3, i % 2 == 0 ? "男" : "女");
				pstmt.addBatch();
			}
			
			// 将这批sql语句发送至mysql服务器
			long start = System.currentTimeMillis();
			pstmt.executeBatch();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)// 防止空指针异常
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
