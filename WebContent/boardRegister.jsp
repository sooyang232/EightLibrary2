<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
                           <li><a href="#">커뮤니티</a></li>
                           <li><a href="#">게시판 문의</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">자유 게시판</h2>
               </div>
               
               <div class="board regist-area">
                   <form name="registForm" id="registForm" method="post"
                   		 action="writePro.do" onsubmit="return writeSave()">
                   		 
                   		 <input type="hidden" name="b1_num" value="${b1_num}">
                   		  
                       <table class="table th-bg">
                           <colgroup>
                               <col width="15%">
                               <col>
                           </colgroup>
                           <tbody>
                               <tr>
                                   <th scope="row"><label for="b1_title">제목</label></th>
                                   <td><input type="text" class="form" name="b1_title" id="b1_title"></td>
                               </tr>
                               <tr>
                                   <th scope="row"><label for="userID">작성자</label></th>
                                   <td>
                                   	<input type="text" class="form" name="userID" value="${userID}">
                                   </td>
                               </tr>
                              <!--  <tr>
                                   <th scope="row"><label for="secretY">공개여부</label></th>
                                   <td class="bd01td">
                                       <input type="radio" name="secretYn" id="secretN" value="N"> 공개
                                       <input type="radio" name="secretYn" id="secretY" value="Y" checked="checked"> 비공개
                                   </td>
                               </tr> -->
                               <tr>
                                   <th scope="row"><label for="b1_content">내용</label></th>
                                   <td><textarea name="b1_content" id="b1_content" rows="15" class="form" title="상세 내용 입력"></textarea></td>
                               </tr>
                           </tbody>
                       </table>
                       <div class="btn-area tar">
                       	<input type="submit" value="등록" class="btn deep-blue">
	                    <a href="board.do" role="button" class="btn blue-gray">취소</a>
	                </div>
                   </form>
               </div>
              <!--  <div class="btn-area tar">
                   <a href="#" role="button" class="btn deep-blue">등록</a>
                   <a href="#" role="button" class="btn blue-gray">취소</a>
               </div> -->
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>