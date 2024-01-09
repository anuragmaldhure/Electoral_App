<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<h5 style="color: red;">Error Message :${pageContext.exception.message}</h5>
	<h5 style="color: red;">Exception Details : ${pageContext.exception}</h5>
	<h5 style="color: red;"> Error causing Page : ${pageContext.errorData.requestURI}</h5>
	<h5 style="color: red;"> Error  Code : ${pageContext.errorData.statusCode} </h5>
</body>
</html>