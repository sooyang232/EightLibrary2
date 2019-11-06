<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/function.js"></script>
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/sub.css">
</head>
<body>
	<c:import url="header.jsp" />
	
		<div id="content" class="site-content">
			<div class="inner-max">
				<div class="content-area">
					<div class="page-header">
						<div class="breadcrumbs">
							<ul>
								<li><a href="#">Home</a></li>
								<li><a href="#">도서관 소개</a></li>
								<li><a href="#">시설현황</a></li>
							</ul>
						</div>
						<h2 class="page-title">시설현황</h2>
					</div>
					
					<div class="board board-area">
						<table class="table tac">
							<colgroup>
									<col style="width: 33%">
									<col style="width: 33%">
									<col style="width: auto">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">바로바로서비스</th>
										<td>051-510-1800</td>
										<td><a href="mailto:jangey@pusan.ac.kr">jangey@pusan.ac.kr</a></td>
									</tr>
									<tr>
										<th scope="row">중앙도서관 대출/반납</th>
										<td>051-510-1308</td>
										<td><a href="mailto:psh8585@pusan.ac.kr">psh8585@pusan.ac.kr</a></td>
									</tr>
									<tr>
										<th scope="row">새벽벌도서관 대출/반납</th>
										<td>051-510-1303</td>
										<td><a href="mailto:okican78@pusan.ac.kr">okican78@pusan.ac.kr</a></td>
									</tr>
									<tr>
										<th scope="row">도서관자치위원회</th>
										<td>051-510-3326</td>
										<td><a class="link" href="http://cafe.daum.net/dojawi" target="_blank">위원회 카페</a></td>
									</tr>
								</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
	<c:import url="footer.jsp"/>
</body>
</html>