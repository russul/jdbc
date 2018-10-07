package jdbctransaction2;

import java.sql.Connection;
import java.sql.SQLException;



public class Demo {
	public static void main(String[] args) throws SQLException {
//		AccountService accountService = new AccountService();
//		accountService.transfer("ls", "zs", 100);
//		这里就会出现并发问题
		final AccountService accountService = new AccountService();
		new Thread(){
			public void run() {
//				AccountService accountService1 = new AccountService();
				try {
					accountService.transfer("zs", "ls", -100);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread(){
			public void run() {
//				AccountService accountService1 = new AccountService();
				try {
					accountService.transfer("zs", "ls", 200);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
}
