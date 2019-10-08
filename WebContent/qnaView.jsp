<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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
                           <li><a href="#">커뮤니티</a></li>
                           <li><a href="#">게시판 문의</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">게시판 문의</h2>
                   <p class="sub-title">게시판 문의는 로그인해야만 작성할 수 있으며, 비밀글 사용이 가능합니다.<br>게시판 문의 이외에 FAQ, 전화 및 이메일 문의, 주제담당사서 문의를 통해 궁금증을 해결할 수 있습니다.</p>
               </div>
               
               <div class="board view-area">
                   <div class="view-head">
                       <h2 class="article-title">
                          ${article.b2_title }<!-- <span class="item-icon new">새 글</span> -->
                       </h2>
                       <div class="article-meta">
                           <dl class="list colon inline">
                               <dt class="author">작성자</dt>
                               <dd class="author r-sep">${article.userID }</dd>
                               <dt class="date">작성일</dt>
                               <dd class="date r-sep">
                               	<fmt:formatDate value="${article.b2_date}" pattern="yyyy-MM-dd" />
                               </dd>
                               <dt class="view">조회</dt>
                               <dd class="view">${article.b2_view }</dd>
                               <%-- <dd class="view r-sep">${article.b2_view }</dd> --%>
                              <!--  <dt class="comment">댓글</dt>
                               <dd class="comment">0</dd> -->
                           </dl>
                       </div>
                   </div>
                   <div class="view-body">
                       ${article.b2_content }
                   </div>
               </div>
               <div class="btn-area tar">
                   <!-- <a href="boardRegist.html" role="button" class="btn deep-blue">답글쓰기</a> -->
                   <a href="qna.do" role="button" class="btn blue-gray">목록보기</a>
               </div>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>