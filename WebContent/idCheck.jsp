<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:useBean id="userdao" class="db.UserDAO"/>
<%
	//function.js->userID값을 받아서 처리
	String userID = request.getParameter("userID");
	System.out.println("userID=" + userID);

	//중복id체크 메서드 호출
	boolean check = userdao.checkId(userID);
	System.out.println("check=" + check);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복ID체크</title>
<script type="text/javascript" src="js/function.js"></script>
</head>
<body>
	<br>
	<center>
		<b><%=userID%></b>
		<%
			if (check) {
				out.println("는 이미 존재하는 ID입니다<p>");
			} else {
				out.println("는 사용가능 합니다<p>");
			}
		%>
		<a href="#" onClick="self.close()">닫기</a>
	</center>
</body>
</html>