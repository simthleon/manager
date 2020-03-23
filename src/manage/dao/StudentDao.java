package manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.httpclient.util.DateUtil;

import manage.model.PageBean;
import manage.model.studentinfo;
import manage.util.StringUtil;

public class StudentDao {
  
	public ResultSet studentList(Connection con,PageBean pagebean,studentinfo student,String ebrithday,String lbrithday) throws Exception {
		
		StringBuffer sb =new StringBuffer("select * from t_student s,t_class c where s.classId=c.id");
		
		if(StringUtil.isNotEmpty(student.getStuNo())){
			//查询学号
			sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getStuName())){
			//查询学生姓名
			sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getSex())){
			//查询性别
			sb.append(" and s.sex ='"+student.getSex()+"'");
		}
		if(student.getClassId()!=-1){
			//如果前台返回值 则不是-1
			sb.append(" and s.classId ='"+student.getClassId()+"'");
		}
		if(StringUtil.isNotEmpty(ebrithday)){
			//查询生日 TO_DAYS为 mysql独有的语句
			sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+ebrithday+"')");
		}
		if(StringUtil.isNotEmpty(lbrithday)){
			//查询生日 TO_DAYS为 mysql独有的语句
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+lbrithday+"')");
		}
		
		
		if(pagebean!=null) {
			sb.append(" limit "+pagebean.getStart()+","+pagebean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
		
	}
	
	
	/**
	 * 学生计数
	 * @param con
	 * @param pagebean
	 * @param student
	 * @param ebrithday
	 * @param lbrithday
	 * @return
	 * @throws SQLException
	 */
	public int studentCount(Connection con,studentinfo student,String ebrithday,String lbrithday) throws Exception {
		StringBuffer sb=new StringBuffer("select count(*) as total from t_student s,t_class c where s.classId=c.id");
		
		if(StringUtil.isNotEmpty(student.getStuNo())){
			//查询学号
			sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getStuName())){
			//查询学生姓名
			sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getSex())){
			//查询性别
			sb.append(" and s.sex ='"+student.getSex()+"'");
		}
		if(student.getClassId()!=-1){
			//如果前台返回值 则不是-1
			sb.append(" and s.classId ='"+student.getClassId()+"'");
		}
		if(StringUtil.isNotEmpty(ebrithday)){
			//查询生日 TO_DAYS为 mysql独有的语句
			sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+ebrithday+"')");
		}
		if(StringUtil.isNotEmpty(lbrithday)){
			//查询生日 TO_DAYS为 mysql独有的语句
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+lbrithday+"')");
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
	    ResultSet rs=pstmt.executeQuery();
	    if(rs.next()) {
	    	return rs.getInt("total");
	    }else {
	    	return 0;
	    }
	    
	}
	
	/**
	 * 学生信息删除操作
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int StudentDelete(Connection con,String StuIds)throws Exception{
		String sql="delete from t_student where stuId in ("+StuIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 学生信息添加
	 * @throws Exception 
	 */
	public int StudentAdd(Connection con,studentinfo student) throws Exception {
		String sql="INSERT into t_student VALUES(null,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	    pstmt.setString(1, student.getStuNo());
	    pstmt.setString(2, student.getStuName());
	    pstmt.setString(3, student.getSex());
	    pstmt.setString(4, DateUtil.formatDate(student.getBrithday(),"yyyy-MM-dd"));
	    pstmt.setInt(5, student.getClassId());
	    pstmt.setString(6, student.getEmail());
	    pstmt.setString(7, student.getStuDesc());
	    return pstmt.executeUpdate();
	}
	
	
	/**
	 * 更改操作
	 */
	public int StudentModify(Connection con,studentinfo student) throws Exception {
		String sql="UPDATE t_student SET stuNo=?,stuName=?,sex=?,brithday=?,classId=?,email=?,stuDesc=? where stuId=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	    pstmt.setString(1, student.getStuNo());
	    pstmt.setString(2, student.getStuName());
	    pstmt.setString(3, student.getSex());
	    pstmt.setString(4, DateUtil.formatDate(student.getBrithday(),"yyyy-MM-dd"));
	    pstmt.setInt(5, student.getClassId());
	    pstmt.setString(6, student.getEmail());
	    pstmt.setString(7, student.getStuDesc());
	    pstmt.setInt(8, student.getStuId());
	    return pstmt.executeUpdate();
	}
	
	
	/***
	 * 班级级联删除提醒
	 * @throws SQLException 
	 */
    public boolean getStudentByClassId(Connection con,String classId) throws SQLException {
    	String sql="select * from t_student where classId=?";
    	PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, classId);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
        	return true;
        }else {
        	return false;
        }
    }

}
