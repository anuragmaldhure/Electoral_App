<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
		<c:set var="fn" value="${sessionScope.user_bean.userDetails.firstName}" />
	<c:set var="ln" value="${sessionScope.user_bean.userDetails.lastName}" />
	<c:set var="role" value="${sessionScope.user_bean.userDetails.role}" />
	<h5>Hello , ${fn} ${ln}</h5>
	<c:choose>
		<c:when test="${role eq 'voter'}">
			<h5>Voter logged out....</h5>
			<a href="login.jsp">Visit Again</a>
		</c:when>
		<c:when test="${role eq 'admin'}">
			<h5>Admin logged out....</h5>
			<a href="login.jsp">Visit Again</a>
		</c:when>
	</c:choose>
	<%--clean up of dao layer --%>
	${sessionScope.user_bean.userDao.cleanUp()}
	${sessionScope.candidate_bean.candidateDao.cleanUp()}
	<%--invalidate session --%>
	${pageContext.session.invalidate()}

</body>
</body>
</html>