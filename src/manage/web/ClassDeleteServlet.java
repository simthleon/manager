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
		//��ȡ�ᱻɾ����id
		String delIds=request.getParameter("delIds");
		//��ȡ���ݿ�����
		Connection con=null;
		try {
			con=dbutil.getCon();
			JSONObject result=new JSONObject();
			//�жϰ༶�Ƿ���ѧ��
			String[] str=delIds.split(",");
			for(int i=0;i<str.length;i++) {
				boolean f=studentdao.getStudentByClassId(con,str[i]);
				if(f) {
					result.put("errorIndex", i);
					result.put("errorMsg","�༶������ѧ������ɾ����");
					ResponseUtil.write(response, result);
					return;
				}
			}
			//��ȡ����
			int delNums=classdao.classDelete(con,delIds);
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
