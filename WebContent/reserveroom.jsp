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
                    <form action="reserveroomstatus.jsp">
                        <div class="legend-box">
                            <ul>
                                <li class="type1">이용중</li>
                                <li class="type2">예약가능</li>
                            </ul>
                        </div>
                        <div class="seat-btn">
                            <ul>
                                <li class="type1" title="이용중"><button type="button" disabled="">01</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">02</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">03</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">04</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">05</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">06</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">07</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">08</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">09</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">10</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">11</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">12</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">13</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">14</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">15</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">16</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">17</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">18</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">19</button></li>
                                <li class="type1" title="이용중"><button type="button" disabled="">20</button></li>
                                <li class="type2" title="예약가능"><button type="button">21</button></li>
                                <li class="type2" title="예약가능"><button type="button">22</button></li>
                                <li class="type2" title="예약가능"><button type="button">23</button></li>
                                <li class="type2" title="예약가능"><button type="button">24</button></li>
                                <li class="type2" title="예약가능"><button type="button">25</button></li>
                                <li class="type2" title="예약가능"><button type="button">26</button></li>
                                <li class="type2" title="예약가능"><button type="button">27</button></li>
                                <li class="type2" title="예약가능"><button type="button">28</button></li>
                                <li class="type2" title="예약가능"><button type="button">29</button></li>
                                <li class="type2" title="예약가능"><button type="button">30</button></li>
                                <li class="type2" title="예약가능"><button type="button">31</button></li>
                                <li class="type2" title="예약가능"><button type="button">32</button></li>
                                <li class="type2" title="예약가능"><button type="button">33</button></li>
                                <li class="type2" title="예약가능"><button type="button">34</button></li>
                                <li class="type2" title="예약가능"><button type="button">35</button></li>
                                <li class="type2" title="예약가능"><button type="button">36</button></li>
                                <li class="type2" title="예약가능"><button type="button">37</button></li>
                                <li class="type2" title="예약가능"><button type="button">38</button></li>
                                <li class="type2" title="예약가능"><button type="button">39</button></li>
                                <li class="type2" title="예약가능"><button type="button">40</button></li>
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
                                                	${date}
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row">시간</th>
                                                <td>
                                                	<fmt:formatDate value="${today}" pattern="hh:mm" var="time" />
                                                	<%-- <c:set var="timePlus" value="<%=new Date(new Date().getTime() + 60*60*3*1000)%>" /> --%>
                                                	<%-- <c:set var="timePlus" value="${today.getTime()+60*60*3*1000 }" />
                                                	<fmt:parseDate var="timePlus" value="${timePlus}" pattern="hh:mm" />
                                                	<fmt:formatDate value="${timePlus}" pattern="hh:mm" var="timeEnd" /> --%>
                                                	${time}~(180분)
                                                </td>
                                            </tr>
                                            <tr>
                                                <th scope="row">좌석번호</th>
                                                <td id="seatID">디지털 열람실 </td>
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