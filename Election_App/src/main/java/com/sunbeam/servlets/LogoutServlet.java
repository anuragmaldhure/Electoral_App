package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// to destroy the cookie
		Cookie c = new Cookie("username", "");
		c.setMaxAge(-1);
		resp.addCookie(c);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Logout</title>");
		out.println("<div style = \"text-align:center; background-color:gray\"><br/><br/> <h1>See you soon!</h1><br/><br/> </div>");
		out.println("<div style = \"text-align:center; background-color:lightgray\"><br/><br/>");
		out.println("</head>");
		out.println("<body>");
		out.println("Thank you! <br/><br/>");
		out.println("<a href='index.html'>Login Again</a><br/><br/></div>");
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
