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
import manage.util.DbUtil;
import manage.util.JsonUtil;
import manage.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassDeleteServlet extends HttpServlet {
    DbUtil dbutil=new DbUtil();
    ClassDao classdao=new ClassDao();
    StudentDao studentdao=new StudentDao();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取会被删除的id
		String delIds=request.getParameter("delIds");
		//获取数据库连接
		Connection con=null;
		try {
			con=dbutil.getCon();
			JSONObject result=new JSONObject();
			//判断班级是否有学生
			String[] str=delIds.split(",");
			for(int i=0;i<str.length;i++) {
				boolean f=studentdao.getStudentByClassId(con,str[i]);
				if(f) {
					result.put("errorIndex", i);
					result.put("errorMsg","班级下面有学生不能删除！");
					ResponseUtil.write(response, result);
					return;
				}
			}
			//获取数字
			int delNums=classdao.classDelete(con,delIds);
			if(delNums>0) {
				//放入JSON中
				result.put("success","true");
				result.put("delNums", delNums);
			}else {
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
