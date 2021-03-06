package jdbctransaction3;

import java.sql.Connection;
import java.sql.SQLException;



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
	public void transfer(String from,String to,double money) throws SQLException{
		
		//处理事务
		try {
			JdbcUtils.beginTransaction();
			accountDao.update(from, -money);
			System.out.println(Thread.currentThread().getName()+":减");
			Thread.sleep(10000);
			accountDao.update(to, money);
			System.out.println(Thread.currentThread().getName()+":加");
			JdbcUtils.commitTransaction();
			
		} catch (Exception e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
//	这样做会导致一个新的问题，由于Connection是Java.sql里的东西，他不应该出现在service里。这样会污染service层
//	后续解决

}	