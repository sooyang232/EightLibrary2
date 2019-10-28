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
	<link rel="stylesheet" type="text/css" href="css/main.css">
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
                           <li><a href="#">마이페이지</a></li>
                           <li><a href="#">회원정보</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">회원정보수정</h2>
               </div>
               
               <div class="join-wrap">
                   
                   <div class="regist-wrap">
                       
                       <form name="modifyUserForm" id="modifyUserForm" method="post"
                       			action="updateUser.do" >
                           <table class="table th-bg">
                               <colgroup>
                                   <col width="15%">
                                   <col>
                               </colgroup>
                               <tbody>
                                   <tr>
                                       <th scope="row" class="req"><label for="userName">성명</label></th>
                                       <td>
                                       		<c:out value="${user.userName }" />
                                       </td>
                                   </tr>
                                 
                                   <tr>
                                       <th scope="row" class="req"><label for="userID">회원 ID</label></th>
                                       <td>
                                       		<input type="hidden" name="userID" value="${userID}">
                                           <c:out value="${userID }"/>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userPWD">현재비밀번호</label></th>
                                       <td>
                                           <input type="password" id="userPWD" name="userPWD" value="" class="form" maxlength="16">
                                           <span class="tip">* 비밀번호는 10~16자리의 영문/숫자 또는 영문/숫자/특수문자[!@#$%^&amp;*()] 혼용</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="p_confirm">현재비밀번호 확인</label></th>
                                       <td>
                                           <input type="password" name="p_confirm" id="p_confirm" value="" class="form" maxlength="16">
                                           <span class="tip">* 비밀번호를 한번 더 입력하세요.</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userTel">연락처</label></th>
                                       <td><input type="text" name="userTel" id="userTel" class="form" value="${user.userTel }">
                                           
                                           <span class="tip">* 전화번호 또는 휴대전화번호 중 하나를 입력하셔야 합니다.</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userEmail">이메일주소</label></th>
                                       <td><input type="text" name="userEmail" id="userEmail" class="form" value="${user.userEmail }">                                     
                                       </td>
                                   </tr>                    
                               </tbody>
                           </table>
                           <div class="btn-area tac">
                       <ul>
                           <li><input type="button" class="btn lg deep-blue" id="modifyUserBtn" value="회원정보 수정" onclick="modifyUser();"></li>
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