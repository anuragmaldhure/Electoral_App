package com.sunbeam.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.ICandidateDao;
import com.sunbeam.pojos.Candidate;

@WebServlet("/result")
public class ResultServlet extends HttpServlet{
	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
		processRequest(req, resp);
	}
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
		processRequest(req, resp);
	}
	protected void processRequest(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
		// business logic
		List<Candidate> list = new ArrayList<>();
		try(ICandidateDao dao = new CandidateDao()) {
			list = dao.findAllOrderByVotesDesc();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		// presentation logic
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Candidates</title>");
		
		
		String uname = "";
		Cookie[] arr = req.getCookies();
		if(arr != null) {
			for (Cookie c : arr) {
				if(c.getName().equals("username")) {
					uname = c.getValue();
					break;
				}
			}
		}
		
		out.printf("<div style = \"text-align:center; background-color:gray\"><br/><br/> <h1>Hello  - %s ! The status of the election is as follows</h1><br/><br/> </div>",uname);
		out.println("</head>");
		out.println("<body>");
		out.println("<div style = \"text-align:center; background-color:lightgray\"><br/><br/>");
		out.println("<center>");
		out.println("<table border = 1");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th> id </th>");
		out.println("<th> name </th>");
		out.println("<th> party </th>");
		out.println("<th> votes </th>");
		out.println("<th colspan = 2> action </th>");
		out.println("</tr>");
		out.println("</thead>");
//		for (Candidate c : list)
//			out.printf("<tr> <td> %s </td><td> %s </td><td> %s </td><td> %s </td>"
//					+ "<td><a href='candedit?id=%d'> <img width='28' height='28' src='images/edit.png' alt='Edit'/> </a></td>"
//					+ "<td><a href='canddel?id=%d'> <img width='28' height='28' src='images/delete.png' alt='Delete'/> </a></td><"
//					+ "/tr>\n", 
//			c.getId(), c.getName(), c.getParty(), c.getVotes());
		for (Candidate c : list)
		    out.printf("<tr> <td> %s </td><td> %s </td><td> %s </td><td> %s </td>"
		                + "<td><a href='candedit?id=%d'> <img width='28' height='28' src='images/edit.png' alt='Edit'/> </a></td>"
		                + "<td><a href='canddel?id=%d'> <img width='28' height='28' src='images/delete.png' alt='Delete'/> </a></td></tr>", 
		            c.getId(), c.getName(), c.getParty(), c.getVotes(), c.getId(), c.getId());


//			out.printf("<input type='radio' name='candidate' value='%s'/> %s - %s <br/>\n", 
//											c.getId(), c.getName(), c.getParty());
		out.println("</table>");
		out.println("</center>");
		out.println("<br/><br/><a href='announce'>Announce</a>");
		
		
		
		String msg = (String) req.getAttribute("message");
		if(msg != null)
			out.println("<br/><br/>" + msg);
		
		
		
		out.println("<br/><a href='annoucement.html'>Announcement</a>");
		
		
		
		out.println("<a href='logout'>Logout</a><br/><br/></div>");
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
