package manage.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.dao.StudentDao;
import manage.model.studentinfo;
import manage.model.studentinfo;
import manage.model.PageBean;
import manage.util.DateUtil;
import manage.util.DbUtil;
import manage.util.JsonUtil;
import manage.util.ResponseUtil;
import manage.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentSaveServlet extends HttpServlet {
    DbUtil dbutil=new DbUtil();
    StudentDao Studentdao=new StudentDao();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取信息
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String sex=request.getParameter("sex");
		String brithday=request.getParameter("brithday");
		String classId=request.getParameter("classId");
		String email=request.getParameter("email");
		String stuDesc=request.getParameter("stuDesc");
		//得到修改对象的Id
		String stuId=request.getParameter("stuId");
		
		studentinfo Studentinfo=null;

		try {
			Studentinfo = new studentinfo(stuNo,stuName,sex, DateUtil.formatString(brithday,"yyyy-MM-dd"),Integer.parseInt(classId), email, stuDesc);
		} catch (Exception e1) {
             e1.printStackTrace();
		}
		//把String类型的Id转换为int类型的
		if(StringUtil.isNotEmpty(stuId)) {
			Studentinfo.setStuId(Integer.parseInt(stuId));
		}
		
		//获取数据库连接
		Connection con=null;
		try {
			con=dbutil.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			if(StringUtil.isNotEmpty(stuId)) {
			  saveNums=Studentdao.StudentModify(con, Studentinfo);
			}else {
			  //获取数字
		      saveNums=Studentdao.StudentAdd(con,Studentinfo);
			}
			
			if(saveNums>0) {
				//放入JSON中
				result.put("success","true");
				
			}else {
				result.put("success","true");
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbutil.closecon(con);
		}
	}
   
	
}
