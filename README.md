# 숙소예약관리기능 구현

<img width="1604" alt="Laptop" src="https://github.com/user-attachments/assets/572fd12a-2ba4-4d10-a84f-ef05483a8176" alt="숙소리스트">

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
- admin로그인 관리자권한, 일반유저로그인시 관리자 제한 (인터셉터활용)
- 카카오로그인 구현(사용자 인증 과정을 처리하고, 카카오서버로부터 액세스토큰을 받아 사용자의 정보를 가져오는 과정을 구현)
- 
![로그인창](https://github.com/user-attachments/assets/302b8ec7-edf3-4956-95c5-f314d1bef6a5)

![카카오로그인구현](https://github.com/user-attachments/assets/37aa4576-924b-43ae-bbca-2b54a9386600)


  
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



### 숙소등록 
- 주소API연결
- 카테고리 변수지정 해서 ```th:selected="${accommodation.accommodationCate == 'hotel'}"```로 카테고리 설정
- 첫번째사진이 메인이미지로 출력되게 설정 

![숙소등록](https://github.com/user-attachments/assets/e1f9db57-fb82-4a20-8bda-5eb86d864e86)

- 숙소정보와 객실정보를 뷰에 노출되게 설정
- 첫번째사진이 리스트에 표시되도록 설정
- 금액은 ```th:text="${#numbers.formatInteger(room.roomPeak,0,'COMMA') + '원'}" ```

![숙소수정1](https://github.com/user-attachments/assets/911e0897-7eb3-48b0-97e2-f50ace88aa09)
![숙소수정2](https://github.com/user-attachments/assets/b953134b-d237-4dec-af45-7c7a5eec4aff)



### 공지사항 

#### 공지사항 등록
- 관리자만 글등록가능 
- 공지사항 체크구현
- 다중첨부파일 등록가능 
- 다중 
#### 공지사항 수정 
- 공지사항 수정시 이미지 출력
- 다중 이미지삭제& 이미지추가 기능 


![공지사항글쓰기](https://github.com/user-attachments/assets/d7696908-55e4-49e1-bded-507c409804ac)

