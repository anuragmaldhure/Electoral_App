package com.sunbeam.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		out.println("<div style = \"text-align:center; background-color:gray\"><br/><br/> <h1>Hello Admin! The results of the election are as follows</h1><br/><br/> </div>");
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
		for (Candidate c : list)
			out.printf("<tr> <td> %s </td><td> %s </td><td> %s </td><td> %s </td><td><a href='edit'>Edit</a></td><td><a href='delete'>Delete</a></td></tr>\n", 
			c.getId(), c.getName(), c.getParty(), c.getVotes());
//			out.printf("<input type='radio' name='candidate' value='%s'/> %s - %s <br/>\n", 
//											c.getId(), c.getName(), c.getParty());
		out.println("</table>");
		out.println("</center>");
		out.println("<br/><br/><a href='announce'>Announce</a>");
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
