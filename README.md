# 숙소예약관리기능 구현

## 🔗 프로젝트 환경설정
- Programing Languages : Java, Thymeleaf, Jquery, javascript
- Framework / Library : SpringBoot,ajax ,Oauth2.0, 
- Server : Tomcat
- DB : MySQL
- Tooling / DevOps : STS4, Gradle

## 😀주요기능 

### 회원가입
아이디 중복확인
비밀번호
이메일 (이메일인증번호 클릭시 > 콘솔창으로 인증번호)
주소API연동
카카오로그인 API연동

### 로그인
- 아이디&비밀번호 확인
- admin로그인 관리자권한, 일반유저로그인시 관리자 제한
  
### 검색기능
- 공지사항, 숙소검색시 검색기능
 ```
<if test="keyword != null and keyword.trim() != ''">
            <choose>
            <when test="searchType == 'title'">
            AND title LIKE CONCAT('%', #{keyword}, '%')
            </when>
            <when test="searchType == 'content'">
            AND content LIKE CONCAT('%', #{keyword}, '%')
            </when>
            <otherwise>
            AND (title LIKE CONCAT('%', #{keyword}, '%') OR content LIKE CONCAT('%', #{keyword} ,'%'))
            </otherwise> 
            </choose>
            
        </if>
```


### 유저 페이지


<img width="1604" alt="Laptop" src="https://github.com/user-attachments/assets/572fd12a-2ba4-4d10-a84f-ef05483a8176" alt="숙소리스트">

로그인창

![로그인창](https://github.com/user-attachments/assets/302b8ec7-edf3-4956-95c5-f314d1bef6a5)
카카오로그인 구현
![카카오로그인구현](https://github.com/user-attachments/assets/37aa4576-924b-43ae-bbca-2b54a9386600)



## 관리자페이지


### 숙소등록 
카테고리, 주소, 객실정보
![숙소등록](https://github.com/user-attachments/assets/e1f9db57-fb82-4a20-8bda-5eb86d864e86)

숙소리스트 

![숙소리스트](https://github.com/user-attachments/assets/e4396055-df40-4af4-a527-4db5d381de09)

숙소상세보기겸 업데이트가능
![숙소수정1](https://github.com/user-attachments/assets/911e0897-7eb3-48b0-97e2-f50ace88aa09)

공지사항 
![공지사항글쓰기](https://github.com/user-attachments/assets/d7696908-55e4-49e1-bded-507c409804ac)
