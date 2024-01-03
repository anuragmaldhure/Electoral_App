package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.ICandidateDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.IUserDao;
import com.sunbeam.pojos.User;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Voted</title>");
		
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
	
		
		out.printf("<div style = \"text-align:center; background-color:gray\"><br/><br/> <h1>Dear voter - %s!</h1><br/><br/> </div>", uname);
		out.println("<div style = \"text-align:center; background-color:lightgray\"><br/><br/>");
		out.println("</head>");
		out.println("<body>");
		
		// get current user from the session
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("curUser");
		if(user.getStatus() != 0) { 
			// user is already voted.
			out.printf("You have already voted! You cannot vote again <br/><br/>");
		}
		else {
			String candId = req.getParameter("candidate");
			int id = Integer.parseInt(candId);
			try(ICandidateDao dao = new CandidateDao()) {
				dao.incrementVotes(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		
			// update the status of current user -- as voted
			user.setStatus(1);
			try(IUserDao dao = new UserDao()) {
				dao.updateStatus(user.getId(), true);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			out.println("Your vote is registered successfully. <br/><br/>");
		}
		
		out.println("<a href='logout'>Sign Out</a><br/><br/></div>");
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



