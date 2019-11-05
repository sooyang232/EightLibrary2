
/**
 * 통합회원 
 */

// 팝업창 띄우기
function WndCenterOpen(mypage, myname, w, h, scroll, option) {
    LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
    TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
    settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+option;
    window.open(mypage, myname, settings);
	return;
}

// 좌우 공백을 없애는 함수
function trim(strSource) {
	re = /^\s+|\s+$/g;
	return strSource.replace(re, '');
}

// 왼쪽 공백을 없애는 함수
function ltrim(strSource) {
	re = /^\s+/g;
	return strSource.replace(re, '');
}

// 오른쪽 공백을 없애는 함수
function rtrim(strSource) {
	re = /\s+$/g;
	return strSource.replace(re, '');
}

/**
* 입력값이 NULL인지 체크
*/
function isNull(input) {
    if (input.value == null || input.value == "") {
        return true;
    }
    return false;
}

/**
* 입력값에 스페이스 이외의 의미있는 값이 있는지 체크
* ex) if (isEmpty(form.keyword)) {
*         alert("검색조건을 입력하세요.");
*     }
*/
function isEmpty(input) {
    if (input.value == null || input.value.replace(/ /gi,"") == "") {
        return true;
    }
    return false;
}

/**
* 입력값에 특정 문자(chars)가 있는지 체크
* 특정 문자를 허용하지 않으려 할 때 사용
* ex) if (containsChars(form.name,"!,*&^%$#@~;")) {
*         alert("이름 필드에는 특수 문자를 사용할 수 없습니다.");
*     }
*/
function containsChars(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) != -1)
           return true;
    }
    return false;
}

/**
* 입력값이 특정 문자(chars)만으로 되어있는지 체크
* 특정 문자만 허용하려 할 때 사용
* ex) if (!containsCharsOnly(form.blood,"ABO")) {
*         alert("혈액형 필드에는 A,B,O 문자만 사용할 수 있습니다.");
*     }
*/
function containsCharsOnly(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) == -1)
           return false;
    }
    return true;
}

/**
* 입력값이 알파벳인지 체크
* 아래 isAlphabet() 부터 isNumComma()까지의 메소드가
* 자주 쓰이는 경우에는 var chars 변수를 
* global 변수로 선언하고 사용하도록 한다.
* ex) var uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
*     var lowercase = "abcdefghijklmnopqrstuvwxyz"; 
*     var number    = "0123456789";
*     function isAlphaNum(input) {
*         var chars = uppercase + lowercase + number;
*         return containsCharsOnly(input,chars);
*     }
*/
function isAlphabet(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    return containsCharsOnly(input,chars);
}

/**
* 입력값이 알파벳 대문자인지 체크
*/
function isUpperCase(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return containsCharsOnly(input,chars);
}

/**
* 입력값이 알파벳 소문자인지 체크
*/
function isLowerCase(input) {
    var chars = "abcdefghijklmnopqrstuvwxyz";
    return containsCharsOnly(input,chars);
}

/**
* 입력값에 숫자만 있는지 체크
*/
function isNumber(input) {
    var chars = "0123456789";
    return containsCharsOnly(input,chars);
}

/**
* 입력값이 알파벳,숫자로 되어있는지 체크
*/
function isAlphaNum(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    return containsCharsOnly(input,chars);
}

/**
* 입력값이 숫자,대시(-)로 되어있는지 체크
*/
function isNumDash(input) {
    var chars = "-0123456789";
    return containsCharsOnly(input,chars);
}

/**
* 입력값이 숫자,콤마(,)로 되어있는지 체크
*/
function isNumComma(input) {
    var chars = ",0123456789";
    return containsCharsOnly(input,chars);
}

// 문자열 길이 검사
function isLength(varCk) {
	var varLen = 0;
	var agr = navigator.userAgent;

	for (i=0; i<varCk.length; i++) {
		ch = varCk.charAt(i);
		if ((ch == "\n") || ((ch >= "ㅏ") && (ch <= "히")) || ((ch >="ㄱ") && (ch <="ㅎ")))
			varLen += 2;
		else
			varLen += 1;
	}
	return (varLen);
}

function isYYYYMMDD(y, m, d) {
	switch (m) {
		case 2:        // 2월의 경우
			if (d > 29) return false;
			if (d == 29) {
				// 2월 29의 경우 당해가 윤년인지를 확인
				if ((y % 4 != 0) || (y % 100 == 0) && (y % 400 != 0))
				return false;
			}
			break;
		case 4:        // 작은 달의 경우
		case 6:
		case 9:
		case 11:
			if (d == 31) return false;
	}
	// 큰 달의 경우
	return true;
}

function isNumeric(s) {
	for (i=0; i<s.length; i++) {
    	c = s.substr(i, 1);
		if (c < "0" || c > "9") return false;
	}
	return true;
}

function isSSN(s1, s2) {
	n = 2;
	sum = 0;
	for (i=0; i<s1.length; i++)
		sum += parseInt(s1.substr(i, 1)) * n++;
	for (i=0; i<s2.length-1; i++) {
		sum += parseInt(s2.substr(i, 1)) * n++;
		if (n == 10) n = 2;
	}
	c = 11 - sum % 11;
	if (c == 11) c = 1;
	if (c == 10) c = 0;
	if (c != parseInt(s2.substr(6, 1))) return false;
	else return true;
}

// 이메일 체크 함수
function isValidEmail(email) {
	var invalidChars = "\"|&;<>!*\'\\"   ;
	for (var i = 0; i < invalidChars.length; i++) {
		if (email.indexOf(invalidChars.charAt ) != -1) {
			return false;
		}
   	}
    if (email.indexOf(" ") != -1){
		return false;
	}

	if (window.RegExp) {
		var reg1str = "(@.*@)|(\\.\\.)|(@\\.)|(\\.@)|(^\\.)";
		var reg2str = "^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$";
		var reg1 = new RegExp (reg1str);
		var reg2 = new RegExp (reg2str);
		if (reg1.test(email) || !reg2.test(email)) {
			return false;
		}
	}
	return true;
}

// 재외국인 번호 체크
function check_fgnno(fgnno) {
	var sum=0;
	var odd=0;
	buf = new Array(13);
	for(i=0; i<13; i++) {
		buf[i]=parseInt(fgnno.charAt(i));
	}
	odd = buf[7]*10 + buf[8];
	if(odd%2 != 0) {
		return false;
	}
	if( (buf[11]!=6) && (buf[11]!=7) && (buf[11]!=8) && (buf[11]!=9) ) {
		return false;
	}
	multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
	for(i=0, sum=0; i<12; i++) {
		sum += (buf[i] *= multipliers[i]);
	}
	sum = 11 - (sum%11);
	if(sum >= 10) {
		sum -= 10;
	}
	sum += 2;
	if(sum >= 10) {
		sum -= 10;
	}
	if(sum != buf[12]) {
		return false
	}
	return true;
}

// 주민번호 체크
function check_juminno(juminno) {
	if(juminno=="" || juminno==null || juminno.length!=13) {
		//alert("주민등록번호 자릿수가 적습니다.");
		return false;
	}
	var jumin1 = juminno.substr(0,6);
	var jumin2 = juminno.substr(6,7);
	var yy = jumin1.substr(0,2);				// 년도
	var mm = jumin1.substr(2,2);			// 월
	var dd = jumin1.substr(4,2);				// 일
	var genda = jumin2.substr(0,1);		// 성별
	var msg, ss, cc;

	// 숫자가 아닌 것을 입력한 경우
	if (!isNumeric(jumin1)) {
		//alert("주민등록번호 앞자리를 숫자로 입력하세요.");
		return false;
	}
	// 길이가 6이 아닌 경우
	if (jumin1.length != 6) {
		//alert("주민등록번호 앞자리를 다시 입력하세요.");
		return false;
	}
	// 첫번째 자료에서 연월일(YYMMDD) 형식 중 기본 구성 검사
	if (yy < "00" || yy > "99" || mm < "01" || mm > "12" || dd < "01" || dd > "31") {
		//alert("주민등록번호 앞자리를 다시 입력하세요.");
		return false;
	}
	// 숫자가 아닌 것을 입력한 경우
	if (!isNumeric(jumin2)) {
		//alert("주민등록번호 뒷자리를 숫자로 입력하세요.");
		return false;
	}
	// 길이가 7이 아닌 경우
	if (jumin2.length != 7) {
		//alert("주민등록번호 뒷자리를 다시 입력하세요.");
		return false;
	}
	// 성별부분이 1 ~ 4 가 아닌 경우
	if (genda < "1" || genda > "4") {
		//alert("주민등록번호 뒷자리를 다시 입력하세요.");
		return false;
	}
	// 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
	cc = (genda == "1" || genda == "2") ? "19" : "20";
	// 첫번째 자료에서 연월일(YYMMDD) 형식 중 날짜 형식 검사
	if (isYYYYMMDD(parseInt(cc+yy), parseInt(mm), parseInt(dd)) == false) {
		//alert("주민등록번호 앞자리를 다시 입력하세요.");
		return false;
	}
	// Check Digit 검사
	if (!isSSN(jumin1, jumin2)) {
		//alert("입력한 주민등록번호를 검토한 후, 다시 입력하세요.");
		return false;
	}
	return true;
}

// 사업자등록번호 체크
function check_busino(vencod) {
	var sum = 0;
	var getlist =new Array(10);
	var chkvalue =new Array("1","3","7","1","3","7","1","3","5");
	for(var i=0; i<10; i++) { getlist[i] = vencod.substring(i, i+1); }
	for(var i=0; i<9; i++) { sum += getlist[i]*chkvalue[i]; }
	sum = sum + parseInt((getlist[8]*5)/10);
	sidliy = sum % 10;
	sidchk = 0;
	if(sidliy != 0) { sidchk = 10 - sidliy; }
	else { sidchk = 0; }
	if(sidchk != getlist[9]) { return false; }
	return true;
}

// 포커스 이동
function moveFocus(num,fromform,toform) {
	var strmove = fromform.value.length;
	if(strmove == num) {
		toform.focus();
	}
}

// 날짜 유효성 체크
function isValidDay(yyyy, mm, dd) {
    var m = parseInt(mm,10) - 1;
    var d = parseInt(dd,10);

    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
        end[1] = 29;
    }

    return (d >= 1 && d <= end[m]);
}

// 텍스트박스에 숫자만 (또는 영문만) 입력가능하게 하는 스크립트
/* 사용법
	<input type="text" name="page" onkeypress="filterKey('[0-9]');">
	filterKey함수의 매개변수로 [0-9], [a-z], [A-Z]를 넣어주면 각각 숫자만 영문소문자, 영문대문자만 입력가능하게 한다.  
	[0-9a-z]와같이 동시에 적용이 가능하다.
*/
function filterKey(filter) { 
	if (filter) {
		var sKey = String.fromCharCode(event.keyCode); 
		var re = new RegExp(filter); 
		if (!re.test(sKey)) {
			event.returnValue=false; 
		}
	} 
}

function filterNumber(e, filter) {
	var ek = '';
	if (window.event)
		ek = window.event.keyCode;
	else
		ek = e.which;
	var sKey = String.fromCharCode(ek); 
	var filterKey = filter || '[0-9]';
	var re = new RegExp(filterKey); 
	if (!re.test(sKey)) {
		if (window.event)
			window.event.returnValue = false;
		else
			e.preventDefault();
	}
}

/**** TEXTAREA글자수 제한 ****/
/**** onChange="CheckStrLength(this,20)" onKeyUp="CheckStrLength(this,20)"		 사용시 ****/
function CheckStrLength(field,len) {
	var temp;
	var f = field.value.length;
	var msglen = len; //최대 길이
	var tmpstr = "";
	var strlen;

	for(k=0;k<f;k++) {
		temp = field.value.charAt(k);

		if(escape(temp).length > 4) msglen -= 2;
		else msglen--;
		if(msglen < 0) {
			alert("제한된 글자 수를 초과하셨습니다.");
			field.value = tmpstr;
			break;
		}
		else {
			tmpstr += temp;
		}
	}
}
	
// 파일 체크
var imgMap = {'jpg': true, 'jpeg': true, 'jpe': true, 'png': true, 'bmp': true, 'gif': true, 'pdf': true, 'doc': true, 'docx': true, 'hwp': true, 'ppt': true, 'pptx': true, 'exe': false};
function fileCheck(file) {
	var f = file.value;
	//var ext = f.substring(ext.length-3, ext.length);
	var pos = f.lastIndexOf('.');
	if (pos == -1) 
		return false;
	var ext = f.substring(pos+1);
	ext = ext.toLowerCase();
	if (imgMap[ext])
		return true;
	else
		return false;
}

function checkAttach(tbox) {
	if (tbox.value == '') 
		return ;
	if (!fileCheck(tbox)) {
		var arr = [];
		var idx = 0;
		for (var s in imgMap) {
			if (imgMap[s])
				arr[idx++] = s;
		}
		var supp = arr.join(',');
		alert('지원되지 않는 파일 형식입니다. 지원되는 파일 형식은 \n'+ supp+ '입니다.\n압축파일 및 기타 파일은 지원하지 않습니다.');
		//document.memberForm.upFile.value = '';
		return false;
	}
	return true;
}

// 파일 체크 2
function fileCheck2(file) {
	var ext = file.value;
	ext = ext.substring(ext.length-3,ext.length);
	ext = ext.toLowerCase();
	if( ext == 'jsp' || ext == 'exe' || ext == 'bat' || ext == 'com' || ext == 'php' || ext == 'tml' || ext == 'htm') {
		return false; 
	} else {
		return true;
	}
}

// 창 사이즈 조절
function resizeCenter(w, h) {
	if ( screen.width ) {
		window.moveTo( (screen.width-w)/2, (screen.height-h)/2 );
		window.resizeTo( w, h );
	}
	return false;
}

/**
 * 파일 입력 박스의 값 지우기 (에러가 발생할 수 있음)
 */
function clearFileBox(form, fname) {
	form[fname].select();
	document.selection.clear();
	$(':checkbox[name=attachedFile]').attr('checked', false);
	$('div#attachView').css('display', 'none');
}

/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeString(date) { //formatTime(date)
	var year = date.getFullYear();
	var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
	var day = date.getDate();
//	var hour = date.getHours();
//	var min = date.getMinutes();

	if (("" + month).length == 1) { month = "0" + month; }
	if (("" + day).length == 1) { day = "0" + day; }
//	if (("" + hour).length == 1) { hour = "0" + hour;  }
//	if (("" + min).length == 1) { min = "0" + min;   }
	
	return ("" + year + "/" + month + "/"+ day)
}

// 입력한 날짜가 오늘보다 클 경우 false
function chkYmd(value) {
	var val = value.replace("/","");
	var int_val = Number(val.replace("/",""));
	var today = toTimeString(new Date());
	var int_today = Number(today.replace("/","").replace("/",""));
	
	if(int_val > int_today) {
		return false;
	} else {
		return true;
	}
}	

function addMonth(ymd, pMonth) {
	var val = (ymd.replace("/","")).replace("/","");
	var yyyy = val.substr(0,4);
	var mm = eval(val.substr(4,2) + "- 1") ;
	mm = mm + (pMonth*1);
	var dd = val.substr(6,2);
	
	var cDate; // 계산에 사용할 날짜 객체 선언
	var oDate; // 리턴할 날짜 객체 선언
	var cYear, cMonth, cDay // 계산된 날짜값이 할당될 변수
	cDate = new Date(yyyy, mm, dd) // 계산된 날짜 객체 생성 (객체에서 자동 계산)
	cYear = cDate.getFullYear(); // 계산된 년도 할당
	cMonth = cDate.getMonth(); // 계산된 월 할당
	cDay = cDate.getDate(); // 계산된 일자 할당
	oDate = (dd == cDay) ? cDate : new Date(cYear, cMonth, 0); // 넘어간 월의 첫쨋날 에서 하루를 뺀 날짜 객체를 생성한다.
	return toTimeString(oDate);
}

function inFocus(i) {
	(i).style.border='1px solid #59a509';
}
function outFocus(i) {
	(i).style.border='1px solid #cccccc';
}

function autoheighttextarea(name) {
	var m = 120;
	var s = name.scrollHeight;
	if(s>=m) name.style.pixelHeight=s+4;
}


// idChecked 값 초기화
function initIdChecked() {
	document.memberForm.idChecked.value = "N";
}

function checkEmail(form) {
	// 이메일 체크
	if (!isEmpty(form.e_mail1)) {
		var mailServer = form.selEmail.value;
		if (mailServer == "") {
			alert("이용하시는 메일 서버를 선택 혹은 입력해 주세요"); 
			form.selEmail.focus();
			return false;
		} else if (mailServer == "manual") {
			if (isEmpty(form.mailServer)) {
				alert("이용하시는 메일 서버를 입력해 주세요");
				form.mailServer.focus();
				return false;
			}
			form.e_mail2.value = form.mailServer.value;
		} else {
			form.e_mail2.value = mailServer;
		}
	} else {
		alert("이메일을 입력해주세요");
		return false;
	}
	var email = form.e_mail1.value + "@" + form.e_mail2.value;  
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   

	if(regex.test(email) === false) {  
		alert("잘못된 이메일 형식입니다.");  
		return false;  
	}
	return true;
}
	
//중복id 체크
function idCheck(id){
	var form = document.joinForm;
    if(id==""){
          alert("아이디를 먼저 입력해주세요!");
		  form.userID.focus();
	}else{
		url="idCheck.jsp?userID="+id;
		window.open(url,"post","width=300,height=150,left=800,top=400");
	}
}

// id 중복체크 창 띄우기
function checkId() {
	var form = document.memberForm;
	if (isEmpty(form.user_id)) {
		alert("아이디를 입력해주세요.");
		form.user_id.focus();
		return;
	}
	if (isLength(form.user_id.value)<6 || isLength(form.user_id.value)>12) {
		alert("아이디는 6자이상 12자 이내로 쓸 수 있습니다.");
		form.user_id.focus();
		return;
	}
	if (!isAlphaNum(form.user_id)) {
		alert("아이디는 영어, 숫자만 쓸 수 있습니다.");
		form.user_id.focus();
		return;
	}
	WndCenterOpen('./idCheck.do?user_id='+ form.user_id.value, 'checkId', '430', '337', 'no', '');
}

// id 중복체크 창 띄우기
function checkIdDup() {
	var form = document.joinForm;
	if (isEmpty(form.userID)) {
		alert("아이디를 입력해주세요.");
		form.userID.focus();
		return;
	}
	if (isLength(form.user_id.value)<6 || isLength(form.user_id.value)>12) {
		alert("아이디는 6자이상 12자 이내로 쓸 수 있습니다.");
		form.user_id.focus();
		return;
	}
	if (!isAlphaNum(form.user_id)) {
		alert("아이디는 영어, 숫자만 쓸 수 있습니다.");
		form.user_id.focus();
		return;
	}
	WndCenterOpen('/idCheck.do?user_id='+ form.user_id.value, 'checkId', '430', '337', 'no', '');
}

// 우편번호 찾기 팝업창 띄우기
function findPostNoPopup(formName, zip_code1, zip_code2, addr1) {
	WndCenterOpen('./postFind.do?formName='+formName+'&zip_code1='+zip_code1+'&zip_code2='+zip_code2+'&addr1='+addr1, 'findPostNo', '450', '320', 'yes', '');		
}

// 신착정보서비스 선택시 보여줄 input 을 선택하는 메소드
function changeP(o) {
   	if (o.checked) {
		document.getElementById("sdi_keywordP").style.display = "";
		document.getElementById("sdi_keyword").value = "";
		document.getElementById("sdi_keyword2").value = "";
		document.getElementById("sdi_keyword3").value = "";
		document.getElementById("sdi_keyword").focus();
    } else {
    	document.getElementById("sdi_keywordP").style.display = "none";
		document.getElementById("sdi_keyword").value = "";
		document.getElementById("sdi_keyword2").value = "";
		document.getElementById("sdi_keyword3").value = "";
	}
}


//회원가입
function userReg(){
	document.location="join1.do";
}
function checkAll(){
	if($("#checkAll").is(':checked')){
		$("input[type=checkbox]").prop("checked",true);
	}else{
		$("input[type=checkbox]").prop("checked",false);
	}
}
function bookCheckAll(){
	if($("#bookCheckAll").is(':checked')){
		$("input[type=checkbox]").prop("checked",true);
	}else{
		$("input[type=checkbox]").prop("checked",false);
	}
}
//비어있는 값 체크
function join() {
	var form = document.joinForm;
	
	if (isEmpty(form.userName)) {
		alert("성명을 입력해 주세요.");
		form.userName.focus();
		return;
	}
	// ID 중복 체크 여부 체크
	if (isEmpty(form.userID)) {
		alert("아이디를 입력해주세요.");
		form.userID.focus();
		return;
	}
	// 비밀번호 체크
	if (isEmpty(form.userPWD)) {
		alert("비밀번호를 입력해주세요.");
		form.userPWD.focus();
		return;
	}
	if (isEmpty(form.p_confirm)) {
		alert("비밀번호 확인란을 입력해주세요.");
		form.p_confirm.focus();
		return;
	}
	if (form.userPWD.value != form.p_confirm.value) {
		alert("비밀번호가 일치하지 않습니다. 다시 확인하시고 입력해 주세요.");
		form.p_confirm.focus();
		return;
	}	
	// 전화번호, 휴대전화번호 체크	
	if (isEmpty(form.userTel) ) {
		alert("연락처를 입력해주세요.");
		form.userTel.focus();
		return;
	}
	//이메일 체크
	if(isEmpty(form.userEmail)){
		alert("이메일을 입력해주세요.");
		form.userEmail.focus();
		return;
	}
	form.submit();
}

//회원정보수정 체크
function modifyUser(){
	//alert("modifyUser!");
	var form = document.modifyUserForm;
	
	// 비밀번호 체크
	if (isEmpty(form.userPWD)) {
		alert("비밀번호를 입력해주세요.");
		form.userPWD.focus();
		return;
	}
	if (isEmpty(form.p_confirm)) {
		alert("비밀번호 확인란을 입력해주세요.");
		form.p_confirm.focus();
		return;
	}
	if (form.userPWD.value != form.p_confirm.value) {
		alert("비밀번호가 일치하지 않습니다. 다시 확인하시고 입력해 주세요.");
		form.p_confirm.focus();
		return;
	}
	
	// 전화번호, 휴대전화번호 체크	
	if (isEmpty(form.userTel) ) {
		alert("연락처를 입력해주세요.");
		form.userTel.focus();
		return;
	}
	//이메일 체크
	if(isEmpty(form.userEmail)){
		alert("이메일을 입력해주세요.");
		form.userEmail.focus();
		return;
	}	
	form.submit();
}

/**
 * 장애구분,등급
 */
function verifyDsab() {
	var dsabKind = $('select[name=dsab_kind]');
	var dsabGrd = $('select[name=dsab_grd]');
	var dsabDesc = $(':text[name=dsab_grd_desc]');
	// 비장애: 1
	if (dsabKind.val() != '1' && dsabGrd.val() == '') {
		alert('장애 등급을 확인해 주세요.');
		dsabGrd.attr('disabled', false).attr('selectedIndex', 0).focus();
		return false;
	} else if (dsabGrd.val() == '07' && trim(dsabDesc.val()) == '') {
		alert('장애등급 기타를 선택하셨습니다. 등급을 직접 입력해 주세요.');
		$('span#dsab_grd_desc').show();
		$('span#dsab_grd_desc > :text').val('').focus();
		return false;
	}
	var fileName = document.memberForm.upFile;
	if (fileName.value != '' && !checkAttach(fileName)) {
		return false;
	}
	return true;
}


//도서관 조회 부분
function LibraryListPopup() {
	var popup = null;
	var url = "/user/Register/LibraryList2.jsp?setMode=LOGIN";
	// lyjun 책바다 체크를 해야 도서관 조회 및 이용자 번호를 입력할 수 있다.
	if (!document.memberForm.bookch_yn.checked) {	
		alert("책바다 국가상호대차서비스 이용여부를 선택해야 승인요청 도서관을 조회하실 수 있습니다.");
		document.getElementById("bookch_yn").focus();
		return false;
	}
	popup = window.open(url, "LibraryList", "scrollbars=yes,resizable=no,width=420,height=300");
	if (!popup) {
		alert ('팝업차단을 해제해주셔야 도서관조회를 할 수 있습니다.');
		return false;
		popup.focus();
	}
}

function RegisterUserCardView(MODE, LayerID) {
	if (MODE == 'CLOSE') {
		document.getElementById(LayerID).style.display = "none";
	} else {
		document.getElementById(LayerID).style.display = "";
	}
}

//[정규식] 숫자만 체크
function isNumber(str) {
	var isNum = "^[0-9]*$";
	if (!str.match(isNum)) return false;
	else return true;
}

//항목 빈값 체크
function BlankElementChk(FORMS, ELEMENTS, MSG) {
	var forms = document.forms[FORMS];
	if (forms.elements[ELEMENTS].value == "") {
		alert (MSG);
		forms.elements[ELEMENTS].focus();
		return false;
	} else
		return true;
}

//휴대폰 인증 버튼을 요청한다.
function PhoneRegister() {
	// 받는 전화번호 항목 체크
   	var forms = document.forms["memberForm"];
  	
 	// 공통 항목 체크
 	var FORM = "memberForm";
	ids = BlankElementChk(FORM, "mobile1", "휴대폰번호를 입력하세요.");
	if (ids == false) return false;
	ids = BlankElementChk(FORM, "mobile2", "휴대폰번호를 입력하세요.");
	if (ids == false) return false;
	ids = BlankElementChk(FORM, "mobile3", "휴대폰번호를 입력하세요.");
	if (ids == false) return false;

	// 전화번호는 숫자만 가능
	ids = isNumber(forms.elements['mobile1'].value);
	if (ids == false) {
		alert('휴대폰번호는 숫자만 입력이 가능합니다.');
		forms.elements['mobile2'].focus();
		return false;
	}
	ids = isNumber(forms.elements['mobile2'].value);
	if (ids == false) {
		alert('휴대폰번호는 숫자만 입력이 가능합니다.');
		forms.elements['mobile2'].focus();
		return false;
	}
	ids = isNumber(forms.elements['mobile3'].value);
	if (ids == false) {
		alert('휴대폰번호는 숫자만 입력이 가능합니다.');
		forms.elements['mobile3'].focus();
		return false;
	}

	var phone_no = forms.elements['mobile1'].value
	if (phone_no.charAt(1) != "1") {
		alert("휴대폰번호를 입력하세요");
		return false;
	}
	
	// 휴대폰번호를 만든다.
	var setphone = forms.elements['mobile1'].value;
	setphone += forms.elements['mobile2'].value;
	setphone += forms.elements['mobile3'].value;

	var url = "about:blank";
	var popuph = window.open(url, "PhoneRegID", "scrollbars=no,resizable=no,width=435,height=245");
	if (!popuph) {
		alert ('팝업차단을 해제해주셔야 인증을 확인 할 수 있습니다.');
		return false;
		popuph.focus();
	} else {
		document.forms['HandPhoneRegForms'].target = "PhoneRegID";
		document.forms['HandPhoneRegForms'].elements['phones'].value = setphone;
		document.forms['HandPhoneRegForms'].elements['Process'].value = "RegNoSend";
		document.forms['HandPhoneRegForms'].action = "/user/Register/PhoneReg.jsp";
		document.forms['HandPhoneRegForms'].submit();
	}
}
function typeEmail() {
	var n = $(this).val();
	if (n == "manual") {
		$('#typeEmailHost').css('display', '');
		$(':text[name=mailServer]').val('').focus();
		//$(':text[name=e_mail2]').focus();
	} else {
		$('#typeEmailHost').css('display', 'none');
		$(':text[name=e_mail2]').val(n);
	}
}

function checkDsab(obj) {
	var od = obj.id;
	if (obj.checked) {
		var dsab = $('select[name=dsab_kind]').val(); // $(':radio[name=dsab_kind]:checked').val();
		var dsabGrd = $('select[name=dsab_grd]').val();
		//alert(dsabGrd);
		//if (dsab != '2') {
		if (dsab == '1') {
			$('div#'+ od+ '_mesg').css('display', '');
			obj.checked = false;
			return false;
		}
	}
	$('div#'+ od+ '_mesg').css('display', 'none');
	//changeClass(obj);
}

/* 비밀번호 확인 */
function fnCheckPassword(uid, upw)
{
    if(!/^[a-zA-Z0-9!@#$%^&*()]{10,16}$/.test(upw))
    { 
        alert('비밀번호는 숫자와 영문자 조합 또는 숫자와 영문자, 특수문자 조합으로 10~16자리를 사용해야 합니다.\n특수문자는 !@#$%^&amp;*()만 사용가능합니다.'); 
        return false;
    }
  
    var chk_num = upw.search(/[0-9]/g);
    var chk_eng = upw.search(/[a-z]/ig);

    if(chk_num < 0 || chk_eng < 0)
    {
        alert('비밀번호는 숫자와 영문자를 혼용하여야 합니다.');
        return false;
    }
    
    if(/(\w)\1\1\1/.test(upw))
    {
        alert('비밀번호에 같은 문자를 4번 이상 연속해서 사용하실 수 없습니다.'); 
        return false;
    }

    if(upw.search(uid)>-1)
    {
        alert('ID가 포함된 비밀번호는 사용하실 수 없습니다.'); 
        return false;
    }

    return true;
}

$(function() {
	
//	$("#birthday").datepicker({dateFormat: 'yy/mm/dd', yearRange: '-30:+0'});
	$('#selEmail').change(typeEmail);

//	$(':image[name=checkId]').click(checkId);
//	$(':image[name=findZip]').click(function(e) {
//		findPostNoPopup('memberForm', 'zip_code1', 'zip_code2', 'address1');
//	});
//	$(':image[name=regPhone]').click(PhoneRegister);
//	$(':image[name=findLibrary]').click(LibraryListPopup);
//	$(':image[name=examBtn]').click(function(e) {
//		RegisterUserCardView('OPEN', 'Layer1');
//	});
	//$('#joinBtn').click(join);
	//$('#modifyUserBtn').click(modifyUser);
	// 장애구분
	$('select[name=dsab_kind]').change(function(e) {
		$('tr#libraryFaxTr_2').hide();
//		var idx = this.options.selectedIndex;
		var idx = $('select[name=dsab_kind]').val();
//		var idx = this.val();
		var t = $('select#dsab_grd');
		if (idx == '1') {
			t.attr('selectedIndex', 0).attr('disabled', true);
//			$('tr#upFileTr_2').hide();
			// 장애구분 변경에 따른 음성기기 비활성
			$('div#dsab_part').hide();
			$('div#daisy_part').hide();
			document.memberForm.daisy_yn.checked = "";
			
			$('span#dsab_grd_desc').hide();
			$('div#go_join_btn').hide();
		} else {
			var dsabKind = $('select[name=dsab_kind]').val()
			var grd_sel_html;
			if (dsabKind == '19') {
				grd_sel_html = $('div#dsab_grd_sel_7').html();
				//grd_sel_html = $('div#dsab_grd_sel_6').html();
			} else if (dsabKind == '20') {
				grd_sel_html = $('div#dsab_grd_sel_3').html();
				//grd_sel_html = $('div#dsab_grd_sel_6').html();
			} else {
				grd_sel_html = $('div#dsab_grd_sel_6').html();
			}
			t.html(grd_sel_html);
			var sensitivity = document.memberForm.sensitivity_info.value;
			if ("N" == sensitivity) {
				if (confirm("민감정보 수집 이용에 동의하시겠습니까?")) {
					document.memberForm.sensitivity_info.value = "Y";
				} else {
					this.options.selectedIndex = 0;
					t.attr('selectedIndex', 0).attr('disabled', true);
//					$('tr#upFileTr_2').hide();
					// 장애구분 변경에 따른 음성기기 비활성
					$('div#dsab_part').hide();
					$('div#daisy_part').hide();
					document.memberForm.daisy_yn.checked = "";
					
					$('span#dsab_grd_desc').hide();
					return;
				}
			}
//			$('tr#upFileTr_2').show();
			// 장애구분 변경에 따른 음성기기 활성
			$('div#dsab_part').show();
			$('div#daisy_part').show();
			document.memberForm.daisy_yn.checked = "checked";
			t.attr('disabled', false);
			//$('span#dsab_grd_desc').show();
			if (idx == '2' || idx == '3') {
			 	$('tr#libraryFaxTr_2').show();
			}
			$('div#go_join_btn').show();
			if (idx == '19' || idx == '20' || idx == '99') {
				$("#dsab_sel").hide();
			} else {
				$("#dsab_sel").show();
			}
		}
	});
	
	// 장애등급
	$('select[name=dsab_grd]').change(function(e) {
		var dsabKind = $('select[name=dsab_kind]').val(); // $(':radio[name=dsab_kind]:checked').val();
		// 비장애 [1]
		var dsabGrd = $('select[name=dsab_grd]').val(); //eval(this.options.selectedIndex);
		// 장애 등급이 기타인 경우
		if (dsabGrd == '99') {
			$('span#dsab_grd_desc').show();
			$(':text[name=dsab_grd_desc]').focus();
		} else {
			$('span#dsab_grd_desc').hide();
			$(':text[name=dsab_grd_desc]').blur();
		}
		// for Fax
		var display = 'none';
		if (dsabKind == '2' || dsabKind == '3' || (dsabKind == '4' && (dsabGrd >= 1 && dsabGrd <= 2))) {
			display = '';
		}
		document.getElementById("libraryFaxTr_2").style.display = display;
	});
});
