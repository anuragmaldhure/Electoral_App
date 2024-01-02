package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				if(user.getRole().equals("voter"))
					resp.sendRedirect("candlist"); // go to next servlet for voter
				else // user.getRole().equals("admi")
					resp.sendRedirect("result"); // go to next servlet for admin
			} 
			else {
				out.println("Invalid email or password. <br/><br/>");
				out.println("<a href='index.html'>Login Again</a>");
			}
			out.println("</body>");
			out.println("</html>");
	}
}
