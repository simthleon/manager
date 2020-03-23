package manage.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
   private String dbUrl="jdbc:mysql://localhost:3306/db_studentinfo?useSSL=false";
   private String dbUsername="root";
   private String dbPassword="admin";
   private String dbjdbc="com.mysql.jdbc.Driver";
   
   /**
    * 获得数据库连接
    * @return
 * @throws ClassNotFoundException 
    */
   public  Connection getCon() throws Exception {
	   //注册驱动
	   Class.forName(dbjdbc);
	   //获得连接
	   Connection con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	   return con;
   }
   
   /**
    * 关闭数据库连接(资源释放)
    * 
    */
   public void closecon(Connection con) {
	   if(con!=null) {
		   try {
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
   
   /**
    * 测试数据库是否连接成功
    */
   public static void main(String[] args) {
	DbUtil a=new DbUtil();
	try {
		Connection cn=a.getCon();
		if(cn!=null) {
			System.out.println("数据库连接成功！");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
