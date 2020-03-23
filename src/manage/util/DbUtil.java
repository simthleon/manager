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
    * ������ݿ�����
    * @return
 * @throws ClassNotFoundException 
    */
   public  Connection getCon() throws Exception {
	   //ע������
	   Class.forName(dbjdbc);
	   //�������
	   Connection con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	   return con;
   }
   
   /**
    * �ر����ݿ�����(��Դ�ͷ�)
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
    * �������ݿ��Ƿ����ӳɹ�
    */
   public static void main(String[] args) {
	DbUtil a=new DbUtil();
	try {
		Connection cn=a.getCon();
		if(cn!=null) {
			System.out.println("���ݿ����ӳɹ���");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
