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
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/function.js"></script>
	<link rel="stylesheet" type="text/css" href="css/owl.carousel.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<c:import url="header.jsp"/>

<div id="content" class="site-content">
	<div class="inner-max">
		<div class="content-area">
			<div class="con-row cf">
				<div id="main-search">
					<h2 class="hide">통합검색</h2>
					<div class="search-box">
						<div class="text-area">
							<p>Start your search</p>
						</div>
						<div class="search-area">
							<div class="input-box">
								<form method="" name="" action="">
									<input type="text" name="" title="검색어를 입력하세요." placeholder="검색어를 입력하세요." class="search-input">
									<button type="submit" name="" title="검색" value="" class="search-button"><i class="icon-search"></i></button>
								</form>
							</div>
						</div>
					</div>
				</div>	
				<div id="main-quick-menu">
					<h2 class="hide">바로가기 메뉴</h2>
					<ul class="quick-menu-list">
						<li>
							<a href="#" class="show-item">
								<span class="icon"><img src="img/main/icon-my-library.png" alt="대출/예약" width="60" height="60"></span>
								<span class="name">대출/예약</span>
							</a>
						</li>
						<li>
							<a href="#" class="show-item" data-item-id="open-hours">
								<span class="icon"><img src="img/main/icon-open-hours.png" alt="이용시간" width="60" height="60"></span>
								<span class="name">이용시간</span>
							</a>
						</li>
						<li>
							<a href="#" class="show-item" data-item-id="faq-intro">
								<span class="icon"><img src="img/main/icon-qna.png" alt="이용문의" width="60" height="60"></span>
								<span class="name">이용문의</span>
							</a>
						</li>
						<li>
							<a href="#" class="show-item">
								<span class="icon"><img src="img/main/icon-reading-room.png" alt="열람실 예약" width="60" height="60"></span>
								<span class="name">열람실 예약</span>
							</a>
						</li>
						<li>
							<a href="#" class="show-item" data-item-id="book-award">
								<span class="icon"><img src="img/main/icon-contest.png" alt="다독자 현황" width="60" height="60"></span>
								<span class="name">다독자 현황</span>
							</a>
						</li>
						<li>
							<a href="#" class="show-item" data-item-id="library-map">
								<span class="icon"><img src="img/main/icon-map.png" alt="오시는길" width="60" height="60"></span>
								<span class="name">오시는길</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="con-row">
				<div id="entry-content">
					<div id="library-map" class="main-section display-hide show-the-item tac">
						<h2>오시는길</h2>
						<div id="map">
							<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.380351245246!2d127.02597441564565!3d37.498946479810755!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca159c6edda0d%3A0x43bc7aaada610055!2zS0lD7Lqg7Y287Iqk!5e0!3m2!1sko!2skr!4v1567586697840!5m2!1sko!2skr" width="100%" height="450" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
						</div>
					</div>
					<div id="book-award" class="main-section display-hide show-the-item">
						<h2 style="text-align: center">다독자 현황</h2>
						<table class="table line th-bg th-center tac">
							<colgroup>
								<col style="width: 33%">
								<col style="width: 33%">
								<col style="width: auto">
							</colgroup>
							<thead>
								<tr>
									<th>순위</th>
									<th>이름(아이디)</th>
									<th>점수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>정**(jh*****)</td>
									<td>365</td>
								</tr>
								<tr>
									<td>2</td>
									<td>정**(jh*****)</td>
									<td>364</td>
								</tr>
								<tr>
									<td>3</td>
									<td>정**(jh*****)</td>
									<td>363</td>
								</tr>
								<tr>
									<td>4</td>
									<td>정**(jh*****)</td>
									<td>362</td>
								</tr>
								<tr>
									<td>5</td>
									<td>정**(jh*****)</td>
									<td>361</td>
								</tr>
								<tr>
									<td>6</td>
									<td>정**(jh*****)</td>
									<td>360</td>
								</tr>
								<tr>
									<td>7</td>
									<td>정**(jh*****)</td>
									<td>359</td>
								</tr>
								<tr>
									<td>8</td>
									<td>정**(jh*****)</td>
									<td>358</td>
								</tr>
								<tr>
									<td>9</td>
									<td>정**(jh*****)</td>
									<td>357</td>
								</tr>
								<tr>
									<td>10</td>
									<td>정**(jh*****)</td>
									<td>356</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="open-hours" class="main-section display-hide show-the-item">
						<h2 style="text-align: center">이용시간(하계방학기간, 6월 21일~ 8월 31일)</h2>
						<table class="open-table tac">
							<colgroup>
								<col style="width: 25%">
								<col style="width: 25%">
								<col style="width: 25%">
								<col style="width: 25%">
							</colgroup>
							<tbody>
								<tr>
									<td>
										<h4>중앙/새벽벌도서관</h4>
										<p>
											<strong>자료실</strong><br>월~금 09-18시(토 09-13시)<br>
											<strong>새벽벌도서관 열람실</strong><br>
											<strong><span style="color: #999999">제1열람실</span></strong> 월~일 00-24시<br>
											<strong><span style="color: #999999">제2/1노트북열람실</span></strong>&nbsp;월~일 06-23시<br>
											<strong><span style="color: #999999">그 외 열람실</span></strong> 월~일 06-23시<br>
											<span style="color: #999999"><strong>스터디룸</strong></span> 월~금 09-18시(토 09-13시)
										</p>
									</td>
									<td>
										<h4>중앙/새벽벌도서관</h4>
										<p>
											<strong>자료실</strong><br>월~금 09-18시(토 09-13시)<br>
											<strong>새벽벌도서관 열람실</strong><br>
											<strong><span style="color: #999999">제1열람실</span></strong> 월~일 00-24시<br>
											<strong><span style="color: #999999">제2/1노트북열람실</span></strong>&nbsp;월~일 06-23시<br>
											<strong><span style="color: #999999">그 외 열람실</span></strong> 월~일 06-23시<br>
											<span style="color: #999999"><strong>스터디룸</strong></span> 월~금 09-18시(토 09-13시)
										</p>
									</td>
									<td>
										<h4>중앙/새벽벌도서관</h4>
										<p>
											<strong>자료실</strong><br>월~금 09-18시(토 09-13시)<br>
											<strong>새벽벌도서관 열람실</strong><br>
											<strong><span style="color: #999999">제1열람실</span></strong> 월~일 00-24시<br>
											<strong><span style="color: #999999">제2/1노트북열람실</span></strong>&nbsp;월~일 06-23시<br>
											<strong><span style="color: #999999">그 외 열람실</span></strong> 월~일 06-23시<br>
											<span style="color: #999999"><strong>스터디룸</strong></span> 월~금 09-18시(토 09-13시)
										</p>
									</td>
									<td>
										<h4>중앙/새벽벌도서관</h4>
										<p>
											<strong>자료실</strong><br>월~금 09-18시(토 09-13시)<br>
											<strong>새벽벌도서관 열람실</strong><br>
											<strong><span style="color: #999999">제1열람실</span></strong> 월~일 00-24시<br>
											<strong><span style="color: #999999">제2/1노트북열람실</span></strong>&nbsp;월~일 06-23시<br>
											<strong><span style="color: #999999">그 외 열람실</span></strong> 월~일 06-23시<br>
											<span style="color: #999999"><strong>스터디룸</strong></span> 월~금 09-18시(토 09-13시)
										</p>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="btn-area">
							<a class="close-item" data-item-id="open-hours"><i class="icon-minus"></i> 닫기</a>
						</div>
					</div>
					<div id="notice-board" class="main-section cf">
						<div class="notice-section">
							<h2 class="section-title">공지사항</h2>
							<div class="notice-wrap">
								<ul class="notice-list">
									<li>
										<a href="#">
											<strong class="title">2019년도 『제2회 김진재 SF 어워드』 공모전(총 상금 900만원)</strong>
											<span class="date">19-09-10</span>
										</a>
									</li>
									<li>
										<a href="#">
											<strong class="title">2019년도 『제2회 김진재 SF 어워드』 공모전(총 상금 900만원)</strong>
											<span class="date">19-09-10</span>
										</a>
									</li>
									<li>
										<a href="#">
											<strong class="title">2019년도 『제2회 김진재 SF 어워드』 공모전(총 상금 900만원)</strong>
											<span class="date">19-09-10</span>
										</a>
									</li>
									<li>
										<a href="#">
											<strong class="title">2019년도 『제2회 김진재 SF 어워드』 공모전(총 상금 900만원)</strong>
											<span class="date">19-09-10</span>
										</a>
									</li>
									<li>
										<a href="#">
											<strong class="title">2019년도 『제2회 김진재 SF 어워드』 공모전(총 상금 900만원)</strong>
											<span class="date">19-09-10</span>
										</a>
									</li>
								</ul>
							</div>
							<div class="btn-area">
								<a class="more" href="#"><i class="icon-plus"></i> 더보기</a>
							</div>
						</div>
						<div class="newbook-section">
							<h2 class="section-title">신착도서</h2>
							<div class="rolling-wrap">
								<div class="book-list">
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
									<div class="book-item">
										<a href="#">
											<img src="img/main/book-list1.jpg" alt="JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함" >
										</a>
										<span class="title">JAVA의 정석(3ND EDITION)-자바의 정석 최신 Java 8.0 포함</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="faq-intro" class="main-section tac">
						<h2 class="section-title">이용문의(FAQ)</h2>
						<p class="sub-title">이용에 불편한 점이 있으신가요?</p>
						<p class="center">문의 방법은 <span class="primary"><a class="link" href="/community/faq/">자주 묻는 질문(FAQ)</a></span>, <span class="primary"><a class="link" href="/community/ask-bbs/">게시판 문의</a></span>, 담당자와 <span class="primary"><a class="link" href="/community/ask-tel/">전화 또는 이메일</a></span>로 가능합니다.<br>질문을 남겨주시면 담당자가 최대한 신속하고 정확한 답변을 드릴 것입니다.<br>전화 및 이메일 문의는 아래의 연락처를 참고하세요.</p>
						<div class="table-wrap round">
							<table class="table th-bg light-gray line round tac">
								<caption>테이블 제목 : 제목 셀 1, 제목 셀 2, 제목 셀 3 등으로 구성된 표</caption>
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
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>