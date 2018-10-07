package jdbctransaction2;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 该包里的内容是对jdbctransaction包内容的改进 改进点：将事务处理放在业务层而不再是数据层（Dao）
 * 
 * @author Lenovo
 *
 */

// 这是对JdbcUtil工具类的改进，JdbcUtils使用c3p0数据库连接池获取连接对象
// 必须有配置文件c3p0-config.xml放在src目录下
// 一些必须的jar包要导入

public class JdbcUtils {
	private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
	//专用连接对象
	private static Connection con = null;

	public static Connection getConnection() throws SQLException {
		if (con != null) {//保证每次的是同一个connection对象，如果已经有啦就不再去向线程池索要，因为线程池索要的连接对象是不一样的
			return con;
		}
		return comboPooledDataSource.getConnection();// 直接调用连接池对象的getConnection()获取连接对象，jvm会自动的去找配置文件
	}

	public static void beginTransaction() throws SQLException {
		if(con!=null){
			throw new RuntimeException("已经开启事务，不需要重复开启");
		}
		con=getConnection();
		con.setAutoCommit(false);  //设置手动提交，开启事务
	}

	public static void commitTransaction() throws SQLException {
		if(con==null){
			throw new RuntimeException("您还没有开启事务，不能提交");
		}
		con.commit();
		con.close();
		con=null; //结束事务，标志就是con==null,下次再调用提交，回滚会从连接池中获取新的专用连接对象
	}

	public static void rollbackTransaction() throws SQLException {
		if(con==null){
			throw new RuntimeException("您还没有开启事务，不能回滚");
		}
		con.rollback();
		con.close();
		con=null;               //结束事务，标志就是con==null,下次再调用提交，回滚会从连接池中获取新的专用连接对象
	}
//	判断connection是否要关闭
	public static void releaseConnection(Connection connection) throws SQLException{
		if(con==null){//没有事务，关闭
			connection.close();
		}else if(con!=connection){//connection不是专用连接对象，关闭
			connection.close();
		}//connection是专用连接对象不需要管，他会在事务结束的时候关闭
	}
}
