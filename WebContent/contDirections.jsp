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
							<li><a href="#">찾아오시는길</a></li>
						</ul>
					</div>
					<h2 class="page-title">찾아오시는길</h2>
				</div>

				<div id="library-map" class="main-section display-hide show-the-item tac">

					<div id="map">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.380351245246!2d127.02597441564565!3d37.498946479810755!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca159c6edda0d%3A0x43bc7aaada610055!2zS0lD7Lqg7Y287Iqk!5e0!3m2!1sko!2skr!4v1567586697840!5m2!1sko!2skr"
							width="100%" height="450" frameborder="0" style="border: 0;"
							allowfullscreen=""></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"/>
</body>
</html>