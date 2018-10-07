package jdbctransaction2;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbutils.QueryRunner;

import jdbc.JdbcUtil;

public class AccountDao {
	public void update(String name, double money) {
		/*
		 * 1.创建连接
		 */

		Connection con = null;
		// PreparedStatement pstmt=null;
		// QueryRunner是commons.dbutils的东西，dbutils封装了对数据库的一系列操作
		QueryRunner qr = new QueryRunner();
		try {
			con = JdbcUtils.getConnection();
			String sql = "update account set balance=balance+? where name=?";
			// pstmt=con.prepareStatement(sql);
			// pstmt.setDouble(1, money);
			// pstmt.setString(2, name);
			// pstmt.executeUpdate();
			Object[] param = { money, name };
			qr.update(con, sql, param);
			//考虑一个问题，这里的con要关闭吗？肯定不能直接关闭，因为业务层后续还会使用，需要一个判断，没有事务就关闭，有就不关，
//			但在dao层里没有涉及到事务处理，所以得返回业务层，业务层依赖JdbcUtils,所以我们要在他里面加判断，然后在Dao层调用，由JdbcUtils决定什么时候关
			JdbcUtils.releaseConnection(con);
		} catch (Exception e) {
			throw new RuntimeException(e);

		}

	}
}
