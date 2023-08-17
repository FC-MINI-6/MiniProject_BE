![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=160&section=header&text=Mini%20Project&fontSize=50)
<a name="readme-top"></a>



<!-- PROJECT LOGO -->
<img src="https://github.com/FC-MINI-6/MiniProject_BE/assets/78328327/fd50b2a5-5247-4ca8-9b79-1567cce90e3c" align="right" />



<!-- PROJECT SHIELDS -->
# 온앤오프 (On&Off)
### 🏆 프로젝트 소개
프론트(React), 백엔드(SpringBoot/Java) 각팀이 협업하여 ui/ux부터 배포작업까지 완료한 사이드프로젝트입니다.
프로젝트명은 온앤오프이며, 해당 프로젝트는 클라이언트의 일정관리를 손쉽게 유지관리 할 수 있습니다.
<br>
>**배포링크 : https://on-n-off-mini.netlify.app/**
><br>
>**개발기간 : 2023.07.24 ~ 2023.08.11**




<br><!-- TABLE OF CONTENTS -->
## 📌 프로젝트 구조
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#온앤오프">프로젝트 소개</a>
      <ul>
        <li><a href="#프로젝트 소개">배포링크</a></li>
        <li><a href="#프로젝트 소개">개발기간</a></li>
      </ul>
    </li>
    <li><a href="#프로젝트 구조">프로젝트 구조</a></li>
    <li><a href="#팀원 및 역할">팀원 및 역할</a></li>
    <li>
      <a href="#프로젝트 구성">프로젝트 스택</a>
      <ul>
        <li><a href="#">개발환경</a></li>
        <li><a href="#">사용기술</a></li>
      </ul>
    </li>
    <li><a href="#ERD">ERD</a></li>
    <li><a href="#테이블 설계">테이블 설계</a></li>
    <li>
      <a href="#주요 기능">주요 기능</a>
      <ul>
        <li><a href="#">회원가입</a></li>
        <li><a href="#">로그인</a></li>
        <li><a href="#">개인정보수정</a></li>
        <li><a href="#">개인연차/당직</a></li>
        <li><a href="#">관리자기능</a></li>
        <li><a href="#">사용자간공유</a></li>
      </ul>
    </li>
    <li><a href="API 명세서"></a>API 명세서</li>
  </ol>
</details>

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROJECT PEOPLE -->
## ✨ 팀원 및 역할
| <a href="https://github.com/dali186">김주원</a> | <a href="https://github.com/gosuuk">박성욱</a> | <a href="https://github.com/bornin23">한혜지</a> | <a href="https://github.com/jinakim8">김지나</a> |
| :----------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------: 
| 초기 개발 세팅<br/> 회원가입<br/> 로그인<br/> 개인정보수정<br/> 관리자<br/> 배포<br/> | 연차 페이지<br/> 당직 페이지<br/> 캘린더<br/> 배포<br/> | 연차 페이지<br/> 당직 페이지<br/> 개인정보수정<br/> 캘린더<br/> | 프로젝트 팀장<br/> 프로젝트세팅<br/> 로그인<br/> 

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROJECT IDE -->
## 🛠 프로젝트 스택(백엔드)
* 개발환경 : JAVA 11
* IDE: IntelliJ
* Build: Gradle
* Framework: Spring-Boot 2.7.14
* Database: MySQL
* ORM: Mybatis
* 사용기술
  - Spring Web
  - Spring Data JPA
  - Spring DevTools
  - Spring Security
  - JWT 4.3.0
  - h2database
  - lombok
  - Postman
  - AWS EC2

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROJECT ERD -->
## 📖 ERD
![image](https://github.com/FC-MINI-6/MiniProject_BE/assets/78328327/c1b9c922-338e-4bb4-8316-752b248585b6)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROJECT TABLE -->
## ⚖ 테이블 설계
**1. 유저**
```
CREATE TABLE user_tb
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    email        VARCHAR(20) NOT NULL UNIQUE, --로그인 ID로 사용
    password     VARCHAR(20) NOT NULL, --유효성검사
    username     VARCHAR(20) NOT NULL, --실명,유효성검사
    phone_number VARCHAR(20) NOT NULL, --유효성검사
    position     VARCHAR(20) NOT NULL, --사원, 주임, 대리, 과장, 차장, 부장
    roles        VARCHAR(10) NOT NULL, --note: 일반, 관리자 / ENUM DEFAULT '일반'
    update_date  TIMESTAMP             --개인정보 최종 수정일/논의
);
```
**2. 로그인**
```
CREATE TABLE login_tb
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT         NOT NULL,
    ip         VARCHAR(50) NOT NULL,
    user_agent VARCHAR(10) NOT NULL,
    login_at   TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES user_tb(id)
);
```
**3. 연차**
```
CREATE TABLE dayoff_tb
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    user_id        INT         NOT NULL,
    num_of_dayoff  FLOAT       NOT NULL, --남은 연차 일수
    start_date     DATE        NOT NULL, --시작일
    end_date       DATE        NOT NULL, --종료일
    reason         VARCHAR(30) NOT NULL, --사유
    type           VARCHAR(10) NOT NULL, --연차, 오전반차, 오후반차
    status         VARCHAR(10) NOT NULL, --대기, 승인, 반려 ENUM DEFAULT '대기'
    apply_at       DATE        NOT NULL,
    process_at     DATE,    

    FOREIGN KEY (user_id) REFERENCES user_tb(id)
);
```
**4.당직**
```
CREATE TABLE duty_tb
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT         NOT NULL,
    date        DATE        NOT NULL, --당직 날짜
    reason      VARCHAR(30) NOT NULL, --사유
    status      VARCHAR(10) NOT NULL, --대기, 승인, 반려
    apply_at    DATE        NOT NULL,
    process_at  DATE,

    FOREIGN KEY (user_id) REFERENCES user_tb(id)
);
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROJECT Functional Specification  -->
## 🎀 주요 기능
<h3>🔹 회원가입</h3>
<ol>
  <li>jwt이용, 비밀번호 단방향 암호화</li>
  <li>개인정보있는경우(이름,휴대폰,이메일) AES256양방향 암호화적용,복호화</li>
  <li>관련필드에 DB검색할떄도 검색조건을 암호화</li>
</ol>
<h3>🔹 로그인</h3>
<ol>
  <li>jwt token 인증방식, spring security적용</li>
  <li>login 성공 후 마지막 로그인 성공 날짜 업데이트 적용</li>
  <li>login 성공 후 회원번호, user-agent, client ip, 시간 로그 등록처리</li>
  <li>일반로그인/관리자로그인</li>
</ol>
<h3>🔹 개인정보수정</h3>
<ol>
  <li>개인정보 수정,삭제, 수정된 일자 업데이트</li>
  <li>개인연차/당직 등록</li>
  <li>관리자 (유저의 권한 설정), 로그인 발급된 jwt token검증</li>
  <li>비밀번호변경(단방향 암호화), 전화번호변경</li>
</ol>
<h3>🔹 연차/당직</h3>
<ol>
  <li>신청내역, 사용내역, 등록, 조회, 취소(crud)</li>
  <li>연차/당직 구분컬럼구성, 테이블1개설계</li>
  <li>저장/삭제 api구현필요</li>
  <li>내 연차일 수</li>
</ol>
<h3>🔹 관리자기능</h3>
<ol>
  <li>연차 승인</li>
  <li>연차 반려</li>
</ol>
<h3>🔹 사용자간공유</h3>
<ol>
  <li>데이터 내려줄 조회 api 구현</li>
  <li>월별 캘린더 주간/일간 등 다양하게 표현</li>
</ol>

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- PROJECT API -->
## 📌 API 명세서
![image](https://github.com/FC-MINI-6/MiniProject_BE/assets/78328327/eabdf3c6-c6df-44e9-a6b1-8d868c3f9e44)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


