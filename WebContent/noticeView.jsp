<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
                            <li><a href="#">공지&새소식</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">공지&새소식</h2>
                </div>
                
                <div class="board view-area">
                    <div class="view-head">
                        <h2 class="article-title">${article.b3_title}
                        	<jsp:useBean id="now" class="java.util.Date"/>
							<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
							<fmt:formatDate value="${article.b3_date}" pattern="yyyy-MM-dd" var="date" />
                        	<c:if test="${date==today}">
								<span class="board-default-new-notify">New</span>
							</c:if>
                        </h2>
                        <div class="article-meta">
                            <dl class="list colon inline">
                                <dt class="author">작성자</dt>
                                <dd class="author r-sep">${article.adminID}</dd>
                                <dt class="date">작성일</dt>
                                <dd class="date r-sep"><fmt:formatDate value="${article.b3_date}" pattern="yyyy-MM-dd"/></dd>
                                <dt class="view">조회</dt>
                                <dd class="view">${article.b3_view}</dd>
                            </dl>
                        </div>
                    </div>
                    <div class="view-body">
                    	${article.b3_content}
                    </div>
                </div>
                <div class="btn-area tar">
                    <a href="notice.do" role="button" class="btn blue-gray">목록보기</a>
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
	
</body>
</html>