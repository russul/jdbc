package jdbctransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;




import jdbc.JdbcUtil;
import jdbctransaction2.JdbcUtils;

public class AccountDao {
	public void update(Connection con,String name, double money) {
		/*
		 * 1.创建连接
		 */
		
//		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "update account set balance=balance+? where name=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
		
		
	}
}
