<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Error</title>
</head>
<body>
	<center>
		<c:set var="error" value="로그인에 실패하셨습니다.!" />
		<b><c:out value="${error}" />
			<p>
				<c:set var="identify" value="아이디 및 비밀번호를 다시 확인하시기 바랍니다." />
				<c:out value="${identify}" />
			<p></b> <input type="button" value="다시쓰기" onclick="history.back()">
	</center>
</body>
</html>