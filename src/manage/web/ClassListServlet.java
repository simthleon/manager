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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassListServlet extends HttpServlet {
    DbUtil dbutil=new DbUtil();
    ClassDao classdao=new ClassDao();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String classname=request.getParameter("className");
		if(classname==null) {
			classname="";
		}
		//��ѯ�༶��Ϣ
		Classinfo classinfo=new Classinfo();
		classinfo.setClassName(classname);
        //��ȡҳ��		
		PageBean pagebean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		//��ȡ���ݿ�����
		Connection con=null;
		try {
			con=dbutil.getCon();
			//���ؽ����
			ResultSet rs=classdao.classList(con, pagebean,classinfo);
			//ת����JSON
			JSONArray jsonarray=JsonUtil.formatRsToJsonArray(rs);
			//����JSON����
			JSONObject result=new JSONObject();
			//��ȡ����
			int total=classdao.classCount(con,classinfo);
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
