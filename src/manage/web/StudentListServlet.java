package manage.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.dao.ClassDao;
import manage.dao.StudentDao;
import manage.model.Classinfo;
import manage.model.PageBean;
import manage.model.studentinfo;
import manage.util.DbUtil;
import manage.util.JsonUtil;
import manage.util.ResponseUtil;
import manage.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentListServlet extends HttpServlet {
    DbUtil dbutil=new DbUtil();
    StudentDao studentdao=new StudentDao();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ��
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		//��ȡ����
		String stuname=request.getParameter("stuName");
		String stuNo=request.getParameter("stuNo");
		String sex=request.getParameter("sex");
		String ebrithday=request.getParameter("ebrithday");
		String lbrithday=request.getParameter("lbrithday");
		String classid=request.getParameter("classId");
		
		studentinfo student=new studentinfo(); 
		if(stuNo!=null) {
			student.setStuNo(stuNo);
			student.setStuName(stuname);
			student.setSex(sex);
			if(StringUtil.isNotEmpty(classid)) {
				student.setClassId(Integer.parseInt(classid));
			}
		}
		
        //��ȡҳ��		
		PageBean pagebean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		//��ȡ���ݿ�����
		Connection con=null;
		try {
			con=dbutil.getCon();  
			//���ؽ����
			ResultSet rs=studentdao.studentList(con,pagebean,student,ebrithday,lbrithday);
			//ת����JSON
			JSONArray jsonarray=JsonUtil.formatRsToJsonArray(rs);
			//����JSON����
			JSONObject result=new JSONObject();
			//��ȡ����
			int total=studentdao.studentCount(con,student,ebrithday,lbrithday);
			//����JSON��
			result.put("rows", jsonarray);
			result.put("total",total);
			//��ҳ������JSON
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbutil.closecon(con);
		}
	}
   
	
}
