<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>��� ������</title>
</head>
<body>
<center><h2>���</h2>
<br/>

<% 
request.setCharacterEncoding("UTF-8");
String result = request.getParameter("result");
%>

<%
out.print("<br/>�����  " + result);
%>
</center>
</body>
</html>