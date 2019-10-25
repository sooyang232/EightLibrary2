<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script type="text/javascript" src="js/function.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/sub.css">
</head>
<body>

<c:set var="flag" value="${requestScope.flag}" />

<c:import url="header.jsp"/>

<div id="content" class="site-content">
	<div class="inner-max">
		<div class="content-area">
               <div class="page-header">
                   <div class="breadcrumbs">
                       <ul>
                           <li><a href="index.html">Home</a></li>
                           <li><a href="#">회원정보수정</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">회원정보수정</h2>
               </div>
               
               <div class="join-wrap">
                   
                   <c:choose>
                   	<c:when test="${flag==true}">
	                    <div class="join-box tac">
	                        <p class="join-t1">회원정보수정이 완료되었습니다.</p>
	                        <div class="btn-area tac">
	                            <a href="index.do" class="btn lg deep-blue">메인으로 이동</a>
	                        </div>
	                    </div>
                    </c:when>
                    <c:otherwise>
                    	<p>회원정보수정 실패</p>
                    	<p>다시 확인하고 입력해주세요.</p>
                    	<br>
                    	<a href="usermodify.do?userID=${userID}">다시 입력</a>
                    	
                    </c:otherwise>
                   </c:choose>
               </div>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>