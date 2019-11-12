<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <li><a href="#">예약신청</a></li>
                        </ul>
                    </div>
                    <h2 class="page-title">예약신청</h2>
				</div>
                
                <div class="reserve-seat-wrap">
                    <form action="reserveroomstatus.do?userID=${userID }">
                        <div class="legend-box">
                            <ul>
                                <li class="type1">이용중</li>
                                <li class="type2">예약가능</li>
                            </ul>
                        </div>
                        <div class="seat-btn">
                            <ul>
                            	<c:forEach var="seat" items="${seats}">
                            		<c:if test="${fn:contains(seat.rev_roomCheck,'이용중')}">
                            			<li class="type1" title="이용중"><button type="button" disabled="">${seat.seatID}</button></li>
                            		</c:if>
                            		<c:if test="${fn:contains(seat.rev_roomCheck,'예약가능')}">
                            			<li class="type2" title="예약가능"><button type="button" id="seat" onclick="getSeatID();">${seat.seatID}</button></li>
                            		</c:if>
                            	</c:forEach>
                                
                            </ul>
                        </div>
                        <div class="ls_pop">
                            <div class="popHeader">
                                <h3>디지털열람실 예약내역</h3>
                            </div>
                            <div class="popContents">
                                <div class="infoTable">
                                    <table border="0" cellspacing="0" cellpadding="0">
                                        <colgroup>
                                            <col width="90">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                            <tr>
                                                <th scope="row">날짜</th>
                                                <td>
                                                	<jsp:useBean id="today" class="java.util.Date"/>
                                                	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" var="date" />
                                                	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd hh:mm" var="date_time" />
                                                	${date}
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row">시간</th>
                                                <td>
                                                	<fmt:formatDate value="${today}" pattern="hh:mm" var="time" />
                                                	<c:set var="time3" value="<%=new Date(new Date().getTime() + 60*60*3*1000)%>" />
                                                	<fmt:formatDate value="${time3}" pattern="hh:mm" var="timeEnd" />
                                                	${time}~${timeEnd}(180분)
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row">좌석번호</th>
                                                <td id="seatNum">디지털열람실 </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="notice_info">
                                    <h4 class="title">※ 주의사항</h4>
                                    <ul>
                                        <li>ㆍ예약시간 10분 전부터 이용할 수 있습니다. (단, 9시 이후부터 적용)</li>
                                        <li>ㆍ예약시간에 좌석에서 이용하지 않으면 10분 경과마다 1시간씩 이용시간이 차감됩니다.</li>
                                        <li>ㆍ예약시간 30분 내에 좌석에서 로그인하지 않으면 예약이 취소됩니다.(예약위반).</li>
                                        <li>ㆍ예약 3회 위반 시 3일간 디지털열람실을 이용할 수 없습니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>

						<!-- 회원번호 --><input type="hidden" name="userID" value="${userID}">
						<!-- 좌석번호 --><input type="hidden" name="seatID" >
		                <!-- 예약일시 --><input type="hidden" name="date_time" value="${date_time}">
		                <!-- 예약만기 --><input type="hidden" name="timeEnd" value="${timeEnd}">
						<!-- 예약공간 --><input type="hidden" name="revSpace" value="디지털열람실">
						<!-- 예약상태 --><input type="hidden" name="revCheck" value="예약완료">
						
                        <div class="seat_submit">
                            <input type="submit" class="btn lg deep-blue" value="예약완료">
                        </div> 
                    </form>                   
                </div>
                
			</div>
		</div>
	</div>

	<c:import url="footer.jsp"/>
	
</body>
</html>