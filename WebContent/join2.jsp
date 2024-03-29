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
                       <li class="n3">
                           <div>
                               <span>회원가입완료</span>
                           </div>
                       </li>
                   </ul>
                   <div class="regist-wrap">
                       <h3>회원정보입력</h3>
                       <form name="joinForm" id="joinForm" method="post"
                       			action="join3.do" >
                           <table class="table th-bg">
                               <colgroup>
                                   <col width="15%">
                                   <col>
                               </colgroup>
                               <tbody>
                                   <tr>
                                       <th scope="row" class="req"><label for="userName">성명</label></th>
                                       <td><input type="text" id="userName" name="userName" class="form"></td>
                                   </tr>
                                 
                                   <tr>
                                       <th scope="row" class="req"><label for="userID">회원 ID</label></th>
                                       <td>
                                           <input type="text" id="userID" name="userID" value="" class="form" maxlength="12" >
                                           <input type="button" value="ID중복확인" onClick="idCheck(this.form.userID.value)">
                                           <span class="tip">* 아이디는 6~12자리의 영문 또는 숫자 혼용, 특수문자 제외</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userPWD">비밀번호</label></th>
                                       <td>
                                           <input type="password" id="userPWD" name="userPWD" value="" class="form" maxlength="16">
                                           <span class="tip">* 비밀번호는 10~16자리의 영문/숫자 또는 영문/숫자/특수문자[!@#$%^&amp;*()] 혼용</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="p_confirm">비밀번호 확인</label></th>
                                       <td>
                                           <input type="password" name="p_confirm" id="p_confirm" value="" class="form" maxlength="16">
                                           <span class="tip">* 비밀번호를 한번 더 입력하세요.</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userTel">연락처</label></th>
                                       <td><input type="text" name="userTel" id="userTel" class="form">
                                           
                                           <span class="tip">* 전화번호 또는 휴대전화번호 중 하나를 입력하셔야 합니다.</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userEmail">이메일주소</label></th>
                                       <td><input type="text" name="userEmail" id="userEmail" class="form">                                     
                                       </td>
                                   </tr>                    
                               </tbody>
                           </table>
                           <div class="btn-area tac">
                       <ul>
                           <li><input type="button" class="btn lg deep-blue" id="joinBtn" value="회원가입 신청" onclick="join()"></li>
                           <li><a href="#" class="btn lg blue-gray">취소</a></li>
                       </ul>
                   </div>
                       </form>
                   </div>
              
               </div>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>