<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="db.*,java.util.*" %>
<!DOCTYPE html>
<%-- <%
request.setCharacterEncoding("utf-8");
int start = 1;	//시작 레코드 번호
//int end;				//마지막 레코드 번호
int count=0;		//총 레코드 수
//int number=0;	//페이지별로 시작하는 맨 처음 나오는 게시물 번호
List reserveList = null;	//화면에 출력할 레코드를 저장할 변수

ReadingRoomDAO dbPro = new ReadingRoomDAO();
count=dbPro.getReserveCount();		//select count(*) from board -> member
System.out.println("현재 레코드 수 (count)=>"+count);
if(count > 0){
	reserveList = dbPro.getReserves(start, count);//첫번째 레코드번호,불러올 개수
}
%> --%>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- <script type="text/javascript" src="../jquery-3.4.1.min.js"></script> -->
<title>열람실 예약 테스트</title>
<style>
table{border:1px solid;width:500px;height:200px;
	border-collapse:collapse;}
td{border:1px solid;padding:10px;text-align:center;cursor:pointer;background-color:green;}
/* td:hover{background-color:#1DDB16;} */
.reserveSeat{
	background-color:red;
}
</style>
<script>
//이미 선택된 좌석이 있는지 check
var check=0;
$(function() {
	$('td').click( function() {
		if(check==0){
			if($(this).css('background-color')=='rgb(0, 128, 0)'){
				$(this).toggleClass('reserveSeat');
				$("#seatCheck").html("선택된 좌석은 "+$(this).text()+"입니다.");
				check++;
			}
		}else{
			if($(this).css('background-color')!='rgb(0, 128, 0)'){
				$(this).toggleClass('reserveSeat');
				$("#seatCheck").html("선택된 좌석이 없습니다.");
				check--;
			}else{
				alert('1좌석만 선택 가능합니다.')
			}			
		}	
	});
});
</script>
</head>

<body>
<%-- 열람실 테이블 정보 불러오기 TEST<p>
<%
		for(int i=0;i<reserveList.size();i++){
			ReadingRoomDTO reserve = (ReadingRoomDTO)reserveList.get(i);
	%>
   예약ID:<%=reserve.getReserveID() %>
   회원ID:<%=reserve.getMemberID() %>
   좌석번호:<%=reserve.getSeatNum() %>
   예약시간:<%=reserve.getReserveTime() %>
   예약만기시간:<%=reserve.getReserveEndTime() %>
   예약상태:<%=reserve.getReserveID() %>
      <hr>
  	<% } //for %> --%>
  	<center>
  	<table>
  		<tr>
  			<td>01</td>
  			<td>02</td>
  			<td>03</td>
  			<td>04</td>
  			<td>05</td>
  			<td>06</td>
  		</tr>
  		<tr>
  			<td>07</td>
  			<td>08</td>
  			<td>09</td>
  			<td>10</td>
  			<td>11</td>
  			<td>12</td>
  		</tr>
  	</table>
  	<hr>
	<div id="seatCheck">
	선택된 좌석이 없습니다.
	</div>
  	</center>
</body>
</html>