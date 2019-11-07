<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="userID" value="${sessionScope.idKey}" />
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
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
                            <li><a href="index.do">Home</a></li>
                            <li><a href="myboard1.do?userID=${userID }">마이페이지</a></li>
                            <li><a href="myboard1.do?userID=${userID }">나의 게시글</a></li>
                            <li><a href="myboard2.do?userID=${userID }">자유게시판</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">나의 게시글</h2>
                </div>
                <div class="sub-tab-menu">
                    <ul class="tab-menu-list">
                        <li class="menu-item">
                            <a href="myboard1.do?userID=${userID }"><span>질문 및 답변(Q&A)</span></a>
                        </li>
                        <li class="menu-item active">
                            <a href="myboard2.do?userID=${userID }"><span>자유게시판</span></a>
                        </li>
                    </ul>
                </div>
                
                <div class="board board-area">
                    <table class="table tac">
						<colgroup>
							<col class="board-list-uid">
							<col class="board-list-title">
							<col class="board-list-user">
							<col class="board-list-date">
							<col class="board-list-view">
						</colgroup>
						<thead>
                            <tr>
                                <th class="board-list-uid">번호</th>
                               <th class="board-list-title">제목</th>
                               <th class="board-list-user">작성자</th>
                               <th class="board-list-date">작성일</th>
                               <th class="board-list-view">조회</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${count!=0}">
								<c:forEach var="board2" items="${board2list}">
									<tr>
										<td class="board-list-uid">${board2.b1_num }</td>
										<td class="board-list-title">
										<a href="boardView.do?num=${board2.b1_num}">
										${board2.b1_title }
										</a>
										</td>
										<td class="board-list-user">${board2.userID }</td>
										<td class="board-list-date"><fmt:formatDate value="${board2.b1_date}" pattern="yyyy-MM-dd" /></td>
										<td class="board-list-view">${board2.b1_view }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${count==0 }">
								<tr>
	                                <td colspan="6">나의 게시글이 존재하지 않습니다.</td>
	                            </tr>
                            </c:if>
                        </tbody>
                    </table>
                    
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
</body>
</html>