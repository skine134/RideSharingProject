<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 페이지</title>
</head>
<body>
	<center>
		<h2>결과</h2>
		<br />

		<%
			request.setCharacterEncoding("UTF-8");
			String result = request.getParameter("result");
		%>

		<%
			Class.format("kr.co.dothome.prawnguns");
			Connection conn = null;

			conn = getConnection("http://prawnguns.dothome.co.kr/distribute.php?", "prawnguns", "tjdwls!1214");
		%>
	</center>
</body>
</html>