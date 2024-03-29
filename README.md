# <img src="./WebContent/img/site-logo.png" width="140" height="40">  
이 프로젝트는 "도서 검색 및 열람실 예약 현황을 쉽게 확인할 수 있는 **도서관시스템 프로젝트**"입니다.  

## 개발환경
OS:Window7  
Web Server : ApacheTomcat 8.5  
DB: MariaDB 5.5.60  
Language : Java1.8,JSP2.3,jQuery3.4

## 실행방법
  1. java1.8 설치, eclipse설치, tomcat8.5설치
  2. 메일발송을 위한 mail-1.4.7.jar 라이브러리 필요
  3. EightLibrary2 > WebContent > index.jsp 에서 Run
  
## 구현기능
+ 회원가입/로그인/로그아웃
    + 회원가입시 회원에게 이메일 발송
    + 아이디 찾기
    + 비밀번호 찾기
+ 게시판(공지&새소식,FAQ,Q&A,자유게시판)
    + Q&A 글쓰기 회원ID 자동 입력
    + 작성일이 오늘이면 게시글 제목 옆에 new출력
    + 페이징처리
    + 키워드 검색
+ 통합검색,신착도서
    + openAPI사용하여 도서정보 '88건' DB에 저장
    + 전체/서명/저자/출판사에 따라서 키워드로 검색
+ 마이페이지
    + 관심도서 등록 및 취소  
    + 로그인 시에만 마이페이지 사용 가능
    + 회원정보수정 기능
    + 나의 게시글 확인 기능
    + 예약도서 등록 및 취소
+ 열람실예약
    + 열람실 좌석 예약신청 기능
    + 열람실 예약 확인 기능
    + 열람실 예약 취소 기능

## 벤치마킹
+ 부산대학교 도서관 <https://lib.pusan.ac.kr/>
+ 의왕중앙도서관 <http://www.uwlib.or.kr/jungang/main.do>
+ 국립중앙도서관 디지털도서관 예약 <http://www.nl.go.kr/app/nl/search/reservation/reservation_intro_inner.jsp>

## 팀원
+ jung hyelim  
    + github <https://github.com/limijj>  
    + email <jhl1710@naver.com>  

***  
+ park sooyang  
    + github <https://github.com/sooyang232/>  
    + blog <https://maribel.tistory.com/>  
    + email <sooyang23@gmail.com>  
    
***   
+ ahn juhui  
    + github <https://github.com/1234wngml>  
    + blog <https://soondd.tistory.com/>  
    + email <1234wngml@naver.com>  
    
***
