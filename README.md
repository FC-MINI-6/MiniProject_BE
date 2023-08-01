![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=Mini%20Project&fontSize=50)
# 연차당직 구축사이트(SpringBoot + React)

### 🏆 프로젝트 소개
프론트와 백엔드가 협업하여 만든 사이트입니다. 클라이언트의 일정관리를 손쉽게 유지관리 할 수 있습니다.

### ✨ 개발기간
**2023.07.24 ~ 2023.08.11**

### 🎈 프로젝트 구성
* 멤버구성 : 김주원, 박성욱, 한혜지, 김지나
* 개발환경 : JAVA 11, IDE: IntelliJ, Build: Gradle, Framework: Spring-Boot 2.7.14,
             Database: MySQL, ORM: Mybatis
* 사용기술 : Spring Web, Spring Data JPA, Spring DevTools, Spring Security,
             JWT 4.3.0, h2database, lombok
  
----
### 📌주요 기능

* **회원가입**
  1. jwt이용, 비밀번호 단방향 암호화
  2. 개인정보있는경우(이름,휴대폰,이메일) AES256양방향 암호화적용,복호화
  3. 관련필드에 DB검색할떄도 검색조건을 암호화

* **로그인**
  1. jwt token 인증방식, spring security적용
  2. login 성공 후 마지막 로그인 성공 날짜 업데이트 적용
  3. login 성공 후 회원번호, user-agent, client ip, 시간 로그 등록처리
  4. 일반로그인/관리자로그인

* **개인정보 수정**

  1. 개인정보 수정,삭제, 수정된 일자 업데이트
  2. 개인연차/당직 등록
  3. 관리자 (유저의 권한 설정), 로그인 발급된 jwt token검증
  4. 비밀번호변경(단방향 암호화), 전화번호변경

* **개인 연차/당직**

  1. 신청내역, 사용내역, 등록, 조회, 취소(crud)
  2. 연차/당직 구분컬럼구성, 테이블1개설계
  3. 저장/삭제 api구현필요
  4. 내 연차일 수

* **관리자 기능**

  1. 연차 승인/반려
 
* **사용자간공유**

  1. 데이터 내려줄 조회 api 구현
  2. 월별 캘린더 주간/일간 등 다양하게 표현
