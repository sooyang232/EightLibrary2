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
						<li><a href="#">자료검색</a></li>
					</ul>
				</div>
				<h2 class="page-title">자유 게시판</h2>
			</div>
			<div class="board-search-box tar">
				<form method="post" action="" id="searchForm">
					<div class="input-box">
						<select name="searchKey" title="검색 선택" id="searchKey"
							class="select">
							<option value="all" selected>전체</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="writer">작성자</option>
						</select> <input type="text" name="searchKeyword" title="검색어 입력"
							id="searchKeyword" class="form"> <input type="submit"
							value="검색" title="검색" class="btn primary md">
					</div>
				</form>
			</div>
			<div class="board-area">
				<table class="table tac">
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

						<c:set var="number" value="${pgList.number}" />
						<c:forEach var="article" items="${articleList }">
							<tr>
								<td class="board-list-uid"><c:out value="${number}" /> <c:set
										var="number" value="${number-1}" /></td>
								<td class="board-list-title"><a
									href="/EightLibrary/boardView.do?num=${article.b1_num}&pageNum=${pgList.currentPage}">
										<div class="board-default-cut-strings">
											<!--  도서관에 핸드폰 두고 갔습니다ㅠㅡㅠ여자 화장실에 있는것 같아요. -->
											${article.b1_title}

										</div>
										<div class="board-mobile-contents">
											<span class="contents-item">${article.userID}</span> <span
												class="contents-item">${article.b1_date}</span> <span
												class="contents-item">${article.b1_view}</span>
										</div>
								</a></td>
								<td class="board-list-user">${article.userID}</td>
								<td class="board-list-date"><fmt:formatDate
										value="${article.b1_date}" pattern="yyyy-MM-dd" /></td>
								<td class="board-list-view">${article.b1_view}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination tac">
					<a href="#" class="page-btn prev">이전</a> <a href="#"
						class="page-num current">1</a> <a href="#" class="page-num">2</a>
					<a href="#" class="page-num">3</a> <a href="#" class="page-num">4</a>
					<a href="#" class="page-num">5</a> <a href="#"
						class="page-btn next">다음</a>
				</div>
			</div>
			<div class="btn-area tar mt0">
				<a href="boardRegister.jsp" role="button" class="btn deep-blue">글쓰기</a>
			</div>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>