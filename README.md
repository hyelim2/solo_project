
## 📱 폰 케이스 쇼핑몰 구현

## 📅 개발 기간 (2022/05/01~05/31)

## 💻 개발 환경
> #### OS : Windows 10
> #### 언어 : JAVA, JSP/Servlet(Model2), HTML, CSS, JavaScript
> #### Toos/DB : Eclipse, Apache Tomcat 8.5, MySQL, JDK1.8
<br><br>


## 📄 ER 다이어그램
![er다이어그램](https://user-images.githubusercontent.com/99606477/184591770-5bc47395-02a5-41d7-a376-bd040ace9d91.png)

<br><br>

## 💻 기능 구현
### 1. 메인 페이지
>* 메인 페이지 상단의 배너로 페이지 이동 가능합니다.
![메인페이지](https://user-images.githubusercontent.com/99606477/184591999-274c665e-d572-43c0-bf70-01107f33d3a0.png)

### 2. 회원가입
>* 이미 가입된 ID는 회원가입이 불가능 하도록 중복확인을 걸어두었습니다.
![image](https://user-images.githubusercontent.com/99606477/184679829-50e49de3-17bd-463d-bdde-113bf62e5bb9.png)
### 3. 로그인/로그아웃
>* ID와 PW가 일치해야만 로그인이 가능하며, 로그인 성공한 경우 배너의 로그인 버튼이 로그아웃으로 변경되고 버튼 클릭시 로그아웃 됩니다.
![image](https://user-images.githubusercontent.com/99606477/184681095-3dfc0643-b990-4b6d-9142-7e6e6cfd68cd.png)
### 4. 회원 정보 수정 
> * 정보를 수정하고 update를 누르면 DB에 입력된 정보도 수정이 됩니다.
> ![image](https://user-images.githubusercontent.com/99606477/184681333-b182d2c0-7398-4ad3-b204-a41dcd2a6c35.png)
### 5. 회원 탈퇴 
> * mypage에서 delete를 누르면 회원 탈퇴 페이지로 이동합니다. 비밀번호 입력시 탈퇴되며, DB에도 삭제됩니다.
> ![image](https://user-images.githubusercontent.com/99606477/184681996-14e8f046-fb19-42c5-8997-746fd9b6d068.png)
### 6. 공지 게시판 (admin 계정일 경우만 글쓰기가 가능)
> * admin 계정으로 로그인 된 경우에만 글쓰기 및 수정 삭제가 가능하도록 구현했습니다. 게시판 리스트에 총 5개에 글이 보이도록 구현하고,
> 페이징처리를 했습니다.
> ![image](https://user-images.githubusercontent.com/99606477/184682622-f1d0070b-9bdf-447a-8733-4c85c81465d5.png)
### 7. Q&A 게시판 (파일 업로드/다운로드/게시글 검색)
> * 파일 업로드 및 다운로드가 가능하도록 구현하였고, 게시물 제목을 검색하면 해당 내용이 보여지도록 구현했습니다.
> ![image](https://user-images.githubusercontent.com/99606477/184683091-c49c4f91-ce8b-46c9-8aa4-348b923c1dfe.png)
### 8. 관리자용 상품 등록 게시판
> * 관리자는 상품을 등록하고, 수량 및 등록 상품을 수정할 수 있습니다.
> ![image](https://user-images.githubusercontent.com/99606477/184683998-bb2b27f3-137a-4fcd-94f3-973e5fe15a35.png)
> ![image](https://user-images.githubusercontent.com/99606477/184684146-c2c12b45-62f9-4170-ae71-fda91ed6e416.png)
### 9. 회원용 상품 게시판
> * 회원은 상품 상세 페이지를 확인할 수 있고, 원하는 상품을 장바구니에 담을 수 있습니다.
> ![image](https://user-images.githubusercontent.com/99606477/184684305-1994b3fc-32a8-449e-ac61-4e68250123f4.png)
### 10. 상품 회원 장바구니 담기/ 목록 보기
> * 구매하고자 하는 상품을 장바구니에 담은 목록을 볼 수 있는 페이지입니다.
> ![image](https://user-images.githubusercontent.com/99606477/184684564-0a21d3c2-9a75-40c6-b38a-67ad9b50676e.png)



