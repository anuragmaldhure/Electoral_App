package com.sunbeam.servlets;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.naming.java.javaURLContextFactory;

import com.mysql.cj.util.Util;
import com.sunbeam.daos.IUserDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.pojos.User;

@WebServlet("/signup")
public class SignUp extends HttpServlet{
	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
		String firstNameString = req.getParameter("firstName");
		String lastNameString = req.getParameter("lastName");
		String emailString = req.getParameter("email");
		String passwordString = req.getParameter("passwd");
		String birthDateSrString = req.getParameter("birth"); 
		String roleString  = req.getParameter("role");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date uDate = null;
		try {
			uDate = sdf.parse(birthDateSrString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// business logic
				User user = new User(0, firstNameString, lastNameString, emailString, passwordString, uDate , 0 , roleString);
				try(IUserDao dao = new UserDao()) {
					dao.save(user);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}

		// presentation logic
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<div style = \"text-align:center; background-color:gray\"><br/><br/> <h1>Congratulations! You were registered successfully</h1><br/><br/> </div>");
				out.println("<div style = \"text-align:center; background-color:lightgray\"><br/><br/>");
				out.println("</head>");
				out.println("<body>");
				out.println("User registered successfully! <br/><br/>");
				out.println("<a href='index.html'>Login</a><br/><br/></div>");
				out.println("</body>");
				out.println("<footer>\n"
						+ "	<div style = \"text-align:center; background-color:gray\">\n"
						+ "		<br/>\n"
						+ "		<h3>Electoral App for Sunbeam Sabha 2024 || Developed by @nurag</h3>\n"
						+ "		<br/>\n"
						+ "	</div>\n"
						+ "</footer>");
				out.println("</html>");

	}
	
}
