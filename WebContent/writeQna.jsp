<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                           <li><a href="#">커뮤니티</a></li>
                           <li><a href="#">게시판 문의</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">게시판 문의</h2>
                   <p class="sub-title">게시판 문의는 로그인해야만 작성할 수 있으며, 비밀글 사용이 가능합니다.<br>게시판 문의 이외에 FAQ, 전화 및 이메일 문의, 주제담당사서 문의를 통해 궁금증을 해결할 수 있습니다.</p>
               </div>
               
               <div class="board regist-area">
                   <form name="registForm" id="registForm" method="post"
                   			action="writeProQna.do" onsubmit="return writeSave()">
                   			
                   	<!-- 입력하지 않고 매개변수로 전달해서 테이블에 저장 (hidden) -->  
  						<input type="hidden" name="b2_num" value="${b2_num}">
					<input type="hidden" name="b2_reply" value="${b2_reply}">
					<input type="hidden" name="b2_step" value="${b2_step}">
					
                       <table class="table th-bg">
                           <colgroup>
                               <col width="15%">
                               <col>
                           </colgroup>
                           <tbody>
                               <tr>
                                   <th scope="row"><label for="b2_title">제목</label></th>
                                   <td><input type="text" class="form" name="b2_title" id="b2_title"></td>
                               </tr>
                               <tr>
                                   <th scope="row"><label for="userID">작성자</label></th>
                                   <td>
                                   	<%-- <input type="hidden" name="userID" value="${userID}"> --%>
                                   	<input type="text" class="form" name="userID" id="userID">
                                   </td>
                               </tr>
                               <tr>
                                   <th scope="row"><label for="secretY">공개여부</label></th>
                                   <td class="bd01td">
                                       <input type="radio" name="secretYn" id="secretN" value="N"> 공개
                                       <input type="radio" name="secretYn" id="secretY" value="Y" checked="checked"> 비공개
                                   </td>
                               </tr>
                               <tr>
                                   <th scope="row"><label for="b2_content">내용</label></th>
                                   <td><textarea name="b2_content" id="b2_content" rows="15" class="form" title="상세 내용 입력"></textarea></td>
                               </tr>
                           </tbody>
                       </table>
                       <!-- form 작성 내용 전달위해 form 태그 안에서 전송버튼 필요 -->
                       <div class="btn-area tar">
                       	<input type="submit" value="등록" class="btn deep-blue">
                       	<a href="qna.do" role="button" class="btn blue-gray">취소</a>
                       </div>
                   </form>
               </div>
               <!-- <div class="btn-area tar">
                   <a href="#" role="button" class="btn deep-blue">등록</a>
                   <a href="qna.do" role="button" class="btn blue-gray">취소</a>
               </div> -->
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>