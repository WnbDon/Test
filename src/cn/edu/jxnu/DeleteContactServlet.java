package cn.edu.jxnu;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import cn.edu.jxnu.dao.ContactDao;
import cn.edu.jxnu.model.Contact;
import cn.edu.jxnu.model.User;

public class DeleteContactServlet extends HttpServlet{
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[]  ids=req.getParameterValues("id");
		boolean ret = ContactDao.delete(ids);
		List<Contact> ls;
		User u = (User) req.getSession().getAttribute("user");
		ls = ContactDao.getResults(u);
		req.setAttribute("contacts", ls);
		RequestDispatcher rd =this.getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/success.jsp");
		rd.forward(req, resp);
	}
}
