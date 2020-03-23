package manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manage.model.User;
import manage.util.DbUtil;

public class UserDao {
	/**
	 * µÇÂ¼ÑéÖ¤
	 * @throws SQLException 
	 */
	public User login(Connection con,User user) throws SQLException {
		User resultUser=null;
		String sql="select * from t_user where username=? and password =?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			resultUser=new User();
			resultUser.setUsername(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	
}
