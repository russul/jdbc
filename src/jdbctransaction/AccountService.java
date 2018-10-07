package jdbctransaction;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class AccountService {
	private AccountDao accountDao=new AccountDao();
//	public void transfer(String from,String to,double money){
//	Connection con=JdbcUtil.getConnection();
//	//这样做会在同一个事务里有三个不同的连接对象
//	//处理事务
//	try {
//		con.setAutoCommit(false);
//		accountDao.update(from, -money);
//		accountDao.update(to, money);
//		con.commit();
//		
//	} catch (Exception e) {
//		try {
//			con.rollback();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//	}


// 解决同一个连接对象问题，我们在service层获取连接，然后在以形式参数的形式传递到Dao层
	public void transfer(String from,String to,double money){
		Connection con=JdbcUtil.getConnection();
		//处理事务
		try {
			con.setAutoCommit(false);
			accountDao.update(con, from, -money);
			accountDao.update(con, to, money);
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
//	这样做会导致一个新的问题，由于Connection是Java.sql里的东西，他不应该出现在service里。这样会污染service层
//	后续解决

}	