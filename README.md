# MovieTheater

## 기술 스택 
JAVA, Spring Framework, JPA, Thymeleaf, SpringSecurity, </br>
Spring Data JPA, Querydsl, HTML5, CSS, Javascript, Ajax, </br>
Naver OpenAPI, GitHub

### 개발 기간 : 2021.03 ~ 2021.04 / 2021.08 ~ (리펙토링)

CGV 영화관 사이트를 모티브로 영화관 영화 예매 시스템을 개발해 보았습니다. </br>
사용자는 회원가입을 통해 로그인 한 뒤에 영화 리스트에서 상영중인 영화 목록과 정보를 볼 수 있습니다. </br>
영화 상세 정보에서는 댓글과 평점을 입력할 수 있습니다. </br>
영화 예약 화면에서는 영화, 극장 (시) , 극장지역 (상영관이름), 날짜, 시간을 차례로 선택하게 됩니다. </br>
그 후 예약 상세 화면에서 인원을 고르고 비어있는 곳을 선택한 후 예약 버튼을 누르면 예매가 완료됩니다. </br>
회원 상세 정보 창에서 내가 작성한 댓글과 예약 목록이 나오고, 취소 또는 삭제가 가능합니다. </br>
영화 목록은 영화 목록 Naver API 를 통해 받아오고, 관리자가 선택해서 상영을 시작합니다.</br>

- Spring Security 를 통한 간단한 로그인 기능 구현
- querydsl을 통한 동적 쿼리 검색 기능 구현 (상영중인 영화 검색)
- Ajax 기술을 활용해 restAPI 구현 (댓글, 영화 좌석)
- Controller, Service, Repository, DTO, Domain 패키지로 나누어 유지보수가 쉽게 설계
- Spring Data JPA 사용으로 기본적인 CRUD 해결
- OPEN API 로 받은 데이터 파싱 작업 (JSON, XML → JAVA 객체)
- 테스트 코드 작성 연습 (JUnit)

### 테이블 설계

<img width="520" alt="스크린샷 2021-10-16 오후 10 50 32" src="https://user-images.githubusercontent.com/33217033/137590076-028f0286-dd33-4cbd-99ea-800c88edfe55.png">

<img width="871" alt="스크린샷 2021-10-17 오전 1 34 04" src="https://user-images.githubusercontent.com/33217033/137595198-c1a25d8f-1204-4e54-bb52-f3eb446fdb15.png">

<img width="943" alt="스크린샷 2021-10-17 오전 1 32 54" src="https://user-images.githubusercontent.com/33217033/137595219-6ae2b587-0f69-4da7-8018-0928e1f40340.png">

<img width="1069" alt="스크린샷 2021-10-17 오전 1 33 47" src="https://user-images.githubusercontent.com/33217033/137595254-b1dba300-c4f5-4794-814a-5e7f0beff61e.png">

