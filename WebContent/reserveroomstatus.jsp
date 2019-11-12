<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                            <li><a href="#">열람실예약</a></li>
                            <li><a href="#">예약확인/취소</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">예약확인/취소</h2>
				</div>
                
                <div class="board-search-box tar">
                    <form method="post" action="" id="searchForm">
                        <div class="input-box">
                            <input type="text" name="searchStartDate" id="searchStartDate" value="" style="width: 120px;" placeholder="검색시작일" class="form date hasDatepicker"><img class="ui-datepicker-trigger" src="img/sub/ico_calendar.png" alt="..." title="..."> ~ <input type="text" name="searchEndDate" id="searchEndDate" value="" style="width: 120px;" placeholder="검색종료일" class="form date hasDatepicker"><img class="ui-datepicker-trigger" src="img/sub/ico_calendar.png" alt="..." title="...">
                            <input type="submit" value="검색" title="검색" class="btn primary md">                            
                        </div>
                    </form>
                </div>
                <div class="board board-area">
                    <table class="table tac">
                        <thead>
                            <tr>
                                <th class="seat-list-num">번호</th>
                                <th class="seat-list-date">예약일시</th>
                                <th class="seat-list-room">예약공간</th>
                                <th class="seat-list-id">좌석번호</th>
                                <th class="seat-list-status">예약상태</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:set var="count" value="${count}" />
                        	<c:set var="num" value="1"/>
                        	<c:if test="${count==0 }">
                        		<tr>
	                                <td colspan="5">좌석 예약 내역이 없습니다.</td>
	                            </tr>
                        	</c:if>
                        	<c:forEach var="room" items="${rooms}">
                        		<tr>
	                                <td class="seat-list-num">
	                                	<c:out value="${num}" />
         								<c:set var="num"  value="${num+1}" />
	                                </td>
	                                <td class="seat-list-date">${room.date_time}~${room.timeEnd }</td>
	                                <td class="seat-list-room">${room.revSpace}</td>
	                                <td class="seat-list-id">${room.seatID}</td>
	                                <td class="seat-list-status">
	                                    <c:if test="${fn:contains(room.revCheck,'예약')}">
	                                    	<span class="status available">예약완료</span><br>
	                                    	<button type="button" class="btn loan-off" 
	                                    	onclick="if(confirm('예약좌석을 취소하시겠습니까?'))
	                                		{document.location.href='roomDel.do?userID=${userID}&seatID=${room.seatID}'}">예약취소</button>
	                                    	
										</c:if> 
										<c:if test="${fn:contains(room.revCheck,'취소')}">
											<span class="status not-available">취소완료</span>
										</c:if>
									</td>
	                            </tr>
                        		
                        	</c:forEach>
	                            
	                            
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