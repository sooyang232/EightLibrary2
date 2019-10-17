<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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
                            <li><a href="index.html">Home</a></li>
                            <li><a href="mypage1.html">마이페이지</a></li>
                            <li><a href="basketlist.html">관심도서</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">관심도서</h2>
                </div>
                
                <div class="board board-area">
                    <table class="table tac">
                        <colgroup>
                            <col class="book-list-title">
                            <col class="book-list-writer">
                            <col class="book-list-status">
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="book-list-title">자료명</th>
                                <th class="book-list-writer">저자</th>
                                <th class="book-list-status">관리</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="book-list-title">엔터프라이즈 자바 마이크로서비스 : JVM 기반 대규모 애플리케이션을 마이크로서비스로 설계, 구축, 관리하는 기술</td>
                                <td class="book-list-writer">켄 피니건 지음 ; 오현석 옮김</td>
                                <td class="book-list-status"><button type="button" class="btn loan-off">관심취소</button></td>
                            </tr>
                            
                        </tbody>
                    </table>
                    <div class="pagination tac">
                        <a href="#" class="page-btn prev">이전</a>
                        <a href="#" class="page-num current">1</a>
                        <a href="#" class="page-num">2</a>
                        <a href="#" class="page-num">3</a>
                        <a href="#" class="page-num">4</a>
                        <a href="#" class="page-num">5</a>
                        <a href="#" class="page-btn next">다음</a>
                    </div>
                </div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
</body>
</html>