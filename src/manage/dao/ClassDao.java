package manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manage.model.Classinfo;
import manage.model.PageBean;
import manage.util.StringUtil;

public class ClassDao {
   
	//显示所有的数据
	public ResultSet classList(Connection con,PageBean pagebean,Classinfo calfo) throws SQLException {
		StringBuffer sb=new StringBuffer("select * from t_class");
		if(calfo!=null && StringUtil.isNotEmpty(calfo.getClassName())) {
			sb.append(" and className like '%"+calfo.getClassName()+"%'");
		}
		
		if(pagebean!=null) {
			sb.append(" limit "+pagebean.getStart()+","+pagebean.getRows());
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		return pstmt.executeQuery();
		
	}
	
	public int classCount(Connection con,Classinfo calfo)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_class");
		if(StringUtil.isNotEmpty(calfo.getClassName())) {
			sb.append(" and className like '%"+calfo.getClassName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		}else {
			return 0;
		}
	}
	
	/**
	 * 删除操作  delete from t_calss where id in (1,3,5)
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int classDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_class where id in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 添加操作
	 */
	public int classAdd(Connection con,Classinfo classinfo)throws Exception{
		String sql="INSERT INTO t_class value(null,?,?) ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classinfo.getClassDesc());
		pstmt.setString(2, classinfo.getClassName());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更改操作
	 */
	public int classModify(Connection con,Classinfo classinfo)throws Exception{
		String sql="UPDATE t_class set className=?,classDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,classinfo.getClassName());
		pstmt.setString(2, classinfo.getClassDesc());
		pstmt.setInt(3, classinfo.getId());
		return pstmt.executeUpdate();
	}
}
