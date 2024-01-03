package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.pojos.User;
import com.sunbeam.daos.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String emailString = req.getParameter("email");
		String passwordString = req.getParameter("passwd");
		
		// business logic
		User user = null;
		boolean success = false;
		try(IUserDao dao = new UserDao()) {
			Optional<User> userOpt = dao.findByEmail(emailString);
			if(userOpt.isPresent()) {
				user = userOpt.get();
				if(passwordString.equals(user.getPassword()))
					success = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		
		
	
			// presentation logic
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Login</title>");
			out.println("</head>");
			out.println("<body>");
			if(success) {
				
				// add user object into the session
				HttpSession session = req.getSession();
				session.setAttribute("curUser", user);
				
				
				//Cookie client side implemenetation (javax.servlet.http.Cookie)
				String uname = user.getFirstName() + "_" + user.getLastName();
				Cookie c = new Cookie("username", uname);
				//in seconds
				c.setMaxAge(120);
				resp.addCookie(c);
				
				if(user.getRole().equals("voter"))
					resp.sendRedirect("candlist"); // go to next servlet for voter
				else // user.getRole().equals("admi")
					resp.sendRedirect("result"); // go to next servlet for admin
			} 
			else {
				out.println("<div style = \"text-align:center; background-color:gray\"><br/><br/> <h1>Sorry! You were not logged in</h1><br/><br/> </div>");
				out.println("<div style = \"text-align:center; background-color:lightgray\"><br/><br/>");
				out.println("<br/><br/>Invalid email or password. <br/><br/>");
				out.println("<a href='index.html'>Login Again</a><br/><br/></div>");
				out.println("<footer>\n"
						+ "	<div style = \"text-align:center; background-color:gray\">\n"
						+ "		<br/>\n"
						+ "		<h3>Electoral App for Sunbeam Sabha 2024 || Developed by @nurag</h3>\n"
						+ "		<br/>\n"
						+ "	</div>\n"
						+ "</footer>");
			}
			out.println("</body>");
			out.println("</html>");
	}
}
