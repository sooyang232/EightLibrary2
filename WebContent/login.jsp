<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="userID" value="${sessionScope.idKey}" />
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/sub.css">
</head>
<body>
<c:import url="header.jsp"/>

<div id="content" class="site-content">
	<div class="inner-max">
		<div class="content-area">
               <div class="page-header">
                   <div class="breadcrumbs">
                       <ul>
                           <li><a href="#">Home</a></li>
                           <li><a href="#">로그인</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">로그인</h2>
               </div>
               
               <c:if test="${!empty userID}">
               	<center>
				<b><c:out value="${userID}"/></b>님 환영합니다.
				</center>
			</c:if>
			<c:if test="${empty userID }">
               <div class="login-wrap">
                   <form name="login" method="post" action="loginProc.do">
                       <div class="login-input">
                           <input type="text" title="아이디 입력창" placeholder="아이디" name="userID" id="userID" class="form">
                           <input type="password" title="비밀번호 입력창" placeholder="비밀번호" name="userPWD" id="userPWD" class="form">
                           <div class="login-chk tar">
                               <span class="search-msg">
                                   <a href="#">ID 찾기</a> / <a href="#" class="line">비밀번호 찾기</a>
                               </span>
                           </div>
                           <div class="login-btn">
                               <ul>
                                   <li class="login"><input type="submit" value="로그인" class="btn lg deep-blue"></li>
                                   <li class="join"><a href="join1.do" class="btn lg blue-gray">회원가입</a></li>
                               </ul>
                           </div>
                       </div>
                   </form>
               </div>
               </c:if>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>