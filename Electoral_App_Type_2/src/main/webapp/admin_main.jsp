<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
</head>
<body>

	<%--send admin name n role to the clnt--%>
	<%--session.getAttribute("user_bean").getUserDetails().getFirstName() --%>
	<h5>Hello , ${sessionScope.user_bean.userDetails.firstName}
		${sessionScope.user_bean.userDetails.lastName}</h5>
	<h6>Currently logged in under
		${sessionScope.user_bean.userDetails.role} role</h6>
	<%--JSP invokes JB's method to get top 2 candidate details --%>
	<table style="background-color: lightgrey; margin: auto" border="1">
		<caption>Top 2 Candidates</caption>
		<c:forEach var="candidate"
			items="${sessionScope.candidate_bean.top2Candidates}">
			<tr>
				<td>${candidate.candidateId}</td>
				<td>${candidate.name}</td>
				<td>${candidate.party}</td>
				<td>${candidate.votes}</td>
			</tr>
		</c:forEach>
	</table>
	<%--JSP invokes JB's method to party votes analysis --%>
	<table style="background-color: lightgrey; margin: auto" border="1">
		<caption>Party Votes Analysis</caption>
		<c:forEach var="entry"
			items="${sessionScope.candidate_bean.fetchPartyAnalysis()}">
			<tr>
				<td>${entry.key}</td>
				<td>${entry.value}</td>
			</tr>
		</c:forEach>
	</table>
	<h4 align="center"><a href="logout.jsp">Log Out</a></h4>
	
</body>
</html>