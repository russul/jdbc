package jdbctransaction3;

import java.sql.Connection;
import java.sql.SQLException;


public class Demo {
	public static void main(String[] args) throws SQLException {
		
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
