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
		//判断用户名或密码是否为空
		if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)) {
			request.setAttribute("error", "用户名或密码为空！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		/**
		 * 保留用户名和密码到输入框中
		 */
		request.setAttribute("Username", username);
		request.setAttribute("Password", password);
		/**
		 * 对用户名和密码进行验证
		 */
		User user=new User(username,password);
		Connection con=null;
		
		try {
			con = dbutil.getCon();
			//用户登录
			User rs=userdao.login(con, user);
			//对登录进行判断
			if(rs==null) {
				request.setAttribute("error", "用户名或密码错误！");
				//服务器端跳转
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}else{
				//获取Session
				request.getSession().setAttribute("CurrentUser", username);
				
				//客户端跳转
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
