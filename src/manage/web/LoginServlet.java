package manage.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.dao.UserDao;
import manage.model.User;
import manage.util.DbUtil;
import manage.util.StringUtil;

public class LoginServlet extends HttpServlet{
    DbUtil dbutil=new DbUtil();
    UserDao userdao=new UserDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("UserName");
		String password=request.getParameter("PassWord");
		//�ж��û����������Ƿ�Ϊ��
		if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)) {
			request.setAttribute("error", "�û���������Ϊ�գ�");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		/**
		 * �����û��������뵽�������
		 */
		request.setAttribute("Username", username);
		request.setAttribute("Password", password);
		/**
		 * ���û��������������֤
		 */
		User user=new User(username,password);
		Connection con=null;
		
		try {
			con = dbutil.getCon();
			//�û���¼
			User rs=userdao.login(con, user);
			//�Ե�¼�����ж�
			if(rs==null) {
				request.setAttribute("error", "�û������������");
				//����������ת
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}else{
				//��ȡSession
				request.getSession().setAttribute("CurrentUser", username);
				
				//�ͻ�����ת
				response.sendRedirect("main.jsp");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			dbutil.closecon(con);
		}
		
	}

}
