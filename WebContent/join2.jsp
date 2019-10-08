<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>에잇도서관 통합관리시스템</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/function.js"></script>
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
                           <li><a href="index.html">Home</a></li>
                           <li><a href="#">회원가입</a></li>
                       </ul>
                   </div>
                   <h2 class="page-title">회원가입</h2>
               </div>
               
               <div class="join-wrap">
                   <ul class="join-step">
                       <li class="n1 white">
                           <div>
                               <span>약관동의</span>
                           </div>
                       </li>
                       <li class="n2 white">
                           <div>
                               <span>정보입력</span>
                           </div>
                       </li>
                       <li class="n3">
                           <div>
                               <span>회원가입완료</span>
                           </div>
                       </li>
                   </ul>
                   <div class="regist-wrap">
                       <h3>회원정보입력</h3>
                       <form name="joinForm" id="joinForm" method="post"
                       			action="join3.do" >
                           <table class="table th-bg">
                               <colgroup>
                                   <col width="15%">
                                   <col>
                               </colgroup>
                               <tbody>
                                   <tr>
                                       <th scope="row" class="req"><label for="userName">성명</label></th>
                                       <td><input type="text" id="userName" name="userName" class="form"></td>
                                   </tr>
                                 
                                   <tr>
                                       <th scope="row" class="req"><label for="userID">회원 ID</label></th>
                                       <td>
                                           <!-- <input type="text" id="userID" name="userID" value="" class="form" maxlength="12" onkeydown="if (event.keyCode == 13) {checkIdDup(); return false;}" onchange="initIdChecked(); return false;"> -->
                                           <input type="text" id="userID" name="userID" value="" class="form" maxlength="12" >
                                           <!-- <a href="#" title="새창으로 이동" onclick="javascript:checkIdDup(); return false;" class="button"><img src="img/sub/btn_chk_duplicate.png" name="checkId" alt="회원ID 중복확인 새창" title="회원ID 중복확인 새창"></a> -->
                                           <input type="button" value="ID중복확인" onClick="idCheck(this.form.userID.value)">
                                           <span class="tip">* 아이디는 6~12자리의 영문 또는 숫자 혼용, 특수문자 제외</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userPWD">비밀번호</label></th>
                                       <td>
                                           <input type="password" id="userPWD" name="userPWD" value="" class="form" maxlength="16">
                                           <span class="tip">* 비밀번호는 10~16자리의 영문/숫자 또는 영문/숫자/특수문자[!@#$%^&amp;*()] 혼용</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="p_confirm">비밀번호 확인</label></th>
                                       <td>
                                           <input type="password" name="p_confirm" id="p_confirm" value="" class="form" maxlength="16">
                                           <span class="tip">* 비밀번호를 한번 더 입력하세요.</span>
                                       </td>
                                   </tr>
                                   <!-- <tr>
                                       <th scope="row" class="req"><label for="zip_code1">우편번호</label></th>
                                       <td>
                                           <input type="text" id="zip_code1" name="zip_code1" value="" class="form" readonly="readonly" title="우편번호검색후키워드입력">
                                           <a href="javascript:openJusoPopupNew();" class="button" title="새창에서 열림"><img src="img/sub/btn_search_addr.png" alt="도로명주소찾기"></a>
                                       </td>
                                   </tr> -->
                                   <!-- <tr>
                                       <th scope="row" class="req" rowspan="2"><label for="address1">주소</label></th>
                                       <td>
                                           <input type="text" id="address1" name="address1" value="" class="form" size="90" readonly="readonly">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>
                                           <input type="text" id="address2" name="address2" class="form" size="90" maxlength="100" title="나머지주소입력">
                                           <span class="tip">* 나머지 주소를 입력하세요.</span>
                                       </td>
                                   </tr> -->
                                   <tr>
                                       <th scope="row" class="req"><label for="userTel">연락처</label></th>
                                       <td><input type="text" name="userTel" id="userTel" class="form">
                                           <!-- <select name="mobile1" class="select" id="u_tel02" title="휴대폰번호국번">
                                               <option value="">선택</option>
                                               <option value="010">010</option>
                                               <option value="011">011</option>
                                               <option value="016">016</option>
                                               <option value="017">017</option>
                                               <option value="018">018</option>
                                               <option value="019">019</option>
                                               <option value="02">02</option>
                                               <option value="031">031</option>
                                               <option value="032">032</option>
                                               <option value="033">033</option>
                                               <option value="041">041</option>
                                               <option value="042">042</option>
                                               <option value="043">043</option>
                                               <option value="044">044</option>
                                               <option value="051">051</option>
                                               <option value="052">052</option>
                                               <option value="053">053</option>
                                               <option value="054">054</option>
                                               <option value="055">055</option>
                                               <option value="061">061</option>
                                               <option value="062">062</option>
                                               <option value="063">063</option>
                                               <option value="064">064</option>
                                               <option value="070">070</option>
                                           </select>&nbsp;-
                                           <input type="text" name="mobile2" value="" class="form" size="5" maxlength="4" title="휴대폰번호중간자리">&nbsp;-
                                           <input type="text" name="mobile3" value="" class="form" size="5" maxlength="4" title="휴대폰번호뒷자리">
                                           <input type="hidden" name="PhoneReg" value=""> -->
                                           <span class="tip">* 전화번호 또는 휴대전화번호 중 하나를 입력하셔야 합니다.</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="userEmail">이메일주소</label></th>
                                       <td><input type="text" name="userEmail" id="userEmail" class="form">
                                           <!-- <input type="hidden" name="e_mail2" value="">
                                           <input type="text" id="e_mailAddr" name="e_mail1" value="" class="form" title="이메일주소">&nbsp;@
                                           <span id="typeEmailHost" style="display: none;">
                                               <input type="text" name="mailServer" value="" class="form" title="이메일주소">
                                           </span>
                                           <select id="selEmail" class="select" name="selEmail" title="이메일호스트">
                                               <option value="">선택하세요</option>
                                               <option value="manual">직접입력</option>
                                               <option value="netian.com">네띠앙</option>
                                               <option value="korea.com">코리아닷컴</option>
                                               <option value="gmail.com">G메일</option>
                                               <option value="hanmail.net">한메일</option>
                                               <option value="korea.kr">공직메일</option>
                                               <option value="paran.com">파란</option>
                                               <option value="hanmir.com">한미르</option>
                                               <option value="sayclub.com">세이클럽</option>
                                               <option value="empal.com">엠파스(엠팔)</option>
                                               <option value="nate.com">네이트</option>
                                               <option value="hitel.com">하이텔</option>
                                               <option value="yahoo.co.kr">야후</option>
                                               <option value="dreamwiz.com">드림위즈</option>
                                               <option value="unitel.co.kr">유니텔</option>
                                               <option value="naver.com">네이버</option>
                                               <option value="hotmail.com">핫메일</option>
                                               <option value="chollian.net">천리안</option>
                                               <option value="cyworld.com">싸이월드</option>
                                           </select> -->                                        
                                       </td>
                                   </tr>
                                   <!-- <tr>
                                       <th scope="row" class="req"><label for="u_user_type">회원유형</label></th>
                                       <td>
                                           <input type="hidden" name="job" value="U">
                                           <input type="radio" id="user_type1" name="user_type" value="1" checked="checked" onclick="javascript:changeUserType(this.value);"><label for="user_type1">일반회원</label>
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;정책회원&nbsp;(
                                           <input type="radio" id="user_type2" name="user_type" value="2" onclick="javascript:changeUserType(this.value);"><label for="user_type2">공무원</label>
                                           &nbsp;<input type="radio" id="user_type3" name="user_type" value="3" onclick="javascript:changeUserType(this.value);"><label for="user_type3">공공기관(일부 정부부처 포함)</label>
                                           &nbsp;)
                                           <p>
                                           <span class="tip" id="job_B_sel">
                                               &nbsp;<font style="font-weight:bold;color:red;">* 공무원인 경우 GPKI인증 또는 이메일인증을 받으셔야 합니다.</font>
                                               &nbsp;<a href="#" onclick="javascript:CertGPKI();"><img name="regGPKI" src="/img2/btn_gpki.gif" alt="GPKI인증" title="GPKI인증 새창"></a>
                                               &nbsp;<a href="#" onclick="javascript:popupECERT();"><img name="regGPKI" src="/images/btn_send.png" alt="이메일인증" title="이메일인증 새창"></a>
                                           </span>
                                           <span class="tip" id="job_V_sel">
                                               &nbsp;<font style="font-weight:bold;color:red;">* 정책회원 전용 서비스를 이용하려면 이메일인증을 받으셔야 합니다.</font>
                                               &nbsp;<a href="#" onclick="javascript:popupECERT();"><img name="regGPKI" src="/images/btn_send.png" alt="이메일인증" title="이메일인증 새창"></a>
                                           </span>
                                           </p>
                                       </td>
                                   </tr>
                                   <tr id="jobSelB">
                                       <th scope="row" class="req"><label for="official_mail_yn">학술지메일</label></th>
                                       <td>
                                           <input type="checkbox" id="official_mail_yn" name="official_mail_yn" value="1"><label for="official_mail_yn"> 학술지메일서비스신청</label>
                                           <span class="tip">&nbsp;* 신청시 학술지목차메일링서비스를 받으실 수 있습니다.</span>
                                       </td>
                                   </tr>
                                   <tr>
                                       <th scope="row" class="req"><label for="sms_yn">SMS통보여부(선택)</label></th>
                                       <td>
                                           <input type="checkbox" id="sms_yn" name="sms_yn" value="Y"><label for="sms_yn"> SMS통보신청</label>
                                           <span class="tip">&nbsp;* 신청결과를 휴대전화번호로 알려드립니다.</span>
                                       </td>
                                   </tr> -->                                   
                               </tbody>
                           </table>
                           <div class="btn-area tac">
                       <ul>
                           <li><input type="button" class="btn lg deep-blue" id="joinBtn" value="회원가입 신청" onclick="join()"></li>
                           <!-- <li><a href="join3.html" class="btn lg deep-blue" id="joinBtn">회원가입 신청</a></li> -->
                           <li><a href="#" class="btn lg blue-gray">취소</a></li>
                       </ul>
                   </div>
                       </form>
                   </div>
                   <!-- <div class="btn-area tac">
                       <ul>
                           <li><input type="submit" class="btn lg deep-blue" id="joinBtn" value="회원가입 신청"></li>
                           <li><a href="join3.html" class="btn lg deep-blue" id="joinBtn">회원가입 신청</a></li>
                           <li><a href="#" class="btn lg blue-gray">취소</a></li>
                       </ul>
                   </div> -->
               </div>
		</div>
	</div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>