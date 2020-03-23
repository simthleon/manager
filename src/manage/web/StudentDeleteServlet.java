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

public class StudentDeleteServlet extends HttpServlet {
    DbUtil dbutil=new DbUtil();
    StudentDao studentdao=new StudentDao();
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ᱻɾ����id
		String delIds=request.getParameter("delIds");
		//��ȡ���ݿ�����
		Connection con=null;
		try {
			con=dbutil.getCon();
			JSONObject result=new JSONObject();
			//��ȡ����
			int delNums=studentdao.StudentDelete(con,delIds);
			if(delNums>0) {
				//����JSON��
				result.put("success","true");
				result.put("delNums", delNums);
			}else {
				result.put("errorMsg", "ɾ��ʧ��");
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
