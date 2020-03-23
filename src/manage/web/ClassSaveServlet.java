package manage.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.dao.ClassDao;
import manage.model.Classinfo;
import manage.model.PageBean;
import manage.util.DbUtil;
import manage.util.JsonUtil;
import manage.util.ResponseUtil;
import manage.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassSaveServlet extends HttpServlet {
    DbUtil dbutil=new DbUtil();
    ClassDao classdao=new ClassDao();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取信息
		String className=request.getParameter("className");
		String classDesc=request.getParameter("classDesc");
		String id=request.getParameter("id");
		Classinfo classinfo=new Classinfo(className,classDesc);
		if(StringUtil.isNotEmpty(id)) {
			classinfo.setId(Integer.parseInt(id));
		}
		//获取数据库连接
		Connection con=null;
		try {
			con=dbutil.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			if(StringUtil.isNotEmpty(id)) {
			  saveNums=classdao.classModify(con, classinfo);
			}else {
			  //获取数字
		      saveNums=classdao.classAdd(con,classinfo);
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
