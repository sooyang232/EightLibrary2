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
							<li><a href="#">자료현황</a></li>
						</ul>
					</div>
					<h2 class="page-title">자료현황</h2>
				</div>
				
				<div class="board board-area">
					<table class="table tac">
						<tbody>
							<tr>
								<td>
									<p>
										저희 에잇도서관은 88건의 도서 정보를 가지고 있습니다.
									</p>
									<p>
										도서 상세정보는 자료검색 > 통합검색 또는 자료검색 > 신착도서 를 통해 확인하실 수 있습니다.
									</p>
								</td>
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