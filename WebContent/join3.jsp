<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
                           <li><a href="#">회원가입</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">회원가입</h2>
               </div>
               
               <div class="join-wrap">
                   <ul class="join-step">
                       <li class="n1 white">
                           <div>
                               <span>약관동의</span>
                           </div>
                       </li>
                       <li class="n2 white">
                           <div>
                               <span>정보입력</span>
                           </div>
                       </li>
                       <li class="n3 white">
                           <div>
                               <span>회원가입완료</span>
                           </div>
                       </li>
                   </ul>
                   <c:choose>
                   	<c:when test="${flag==true}">
	                    <div class="join-box tac">
	                        <p class="join-t1">회원가입이 완료되었습니다.</p>
	                        <p class="join-t2">※ 관리자 승인 후 로그인이 가능합니다.</p>
	                        <div class="btn-area tac">
	                            <a href="index.do" class="btn lg deep-blue">메인으로 이동</a>
	                        </div>
	                    </div>
                    </c:when>
                    <c:otherwise>
                    	<p>회원가입 실패</p>
                    	<p>다시 확인하고 입력해주세요.</p>
                    	<br>
                    	<a href=join2.do>다시 가입</a>
                    </c:otherwise>
                   </c:choose>
               </div>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>