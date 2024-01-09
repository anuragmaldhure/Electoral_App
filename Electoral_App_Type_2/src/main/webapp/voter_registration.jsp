<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:setProperty property="*" name="user_bean" />
<body>
	<c:set var="status" value="${sessionScope.user_bean.registerUser()}" />
	<c:choose>
		<c:when test="${status eq 'Voter registered'}">
		 ${pageContext.session.invalidate()}
		 <h5>You have registered successfully...</h5>
			<h6>
				<a href="login.jsp">Login Again</a>
			</h6>
		</c:when>
		<c:otherwise>
			<h5>${status}</h5>
			<h6>
				<a href="register.jsp">Retry Registration</a>
			</h6>
		</c:otherwise>
	</c:choose>
</body>
</html>