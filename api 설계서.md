# API 명세서
- ### 기본 포트 : http://localhost:9999/api/v1
  [회원](#회원)  
  [시간표](#시간표)  
  [게시판](#게시판)  
  [화상회의](#화상회의)



## 회원  

### 회원가입
- URL
```
POST /users/register
```
- Request
```json
{  
   "name":"String", 
   "user_id": "String",
   "password": "String",
   "position": "String",
   "school":"String",
   "grade": "INT",
   "classes": "INT",
   "number": "INT",
   "email": "String",
   "birth": "Date"
}
```
- Response
```json
SUCCESS{ "code": 200, "message": "Success"}
FAIL {
    "code": 500, "message": "Server Error",
    "code": 401, "message": "인증 실패"
    "code": 404, "message": "사용자 없음"
    "code": 500, "message": "서버 오류"
}
```

### 로그인
- URL
```
POST /auth/login
```
- Request
```json
{
    "user_id":"String",
    "password":"String"
}  
```
- Response
```json
SUCCESS{ 
    "accessToken" : "String",
    "code": 200, 
    "message": "Success"
}
FAIL {
    "code": 500, "message": "Server Error",
    "code": 401, "message": "유효하지 않은 password"
}
```

### 내 프로필
-  URL
```
GET /users/<string:user_id>
```
- Request
X
- Response
``` json
SUCCESS{
   "name":"String",    
   "password": "String",
   "position": "String",
   "school":"String",
   "grade": "INT",
   "classes": "INT",
   "number": "INT",
   "email": "String",
   "birth": "DATE"
}
FAIL {
    "code": 500, "message": "Server Error",
    "code": 401, "message": "본인의 ID가 아닙니다.",
}
```
### 회원 정보 수정
- URL
```
PUT /users/<string:user_id>
```
- Request
```json
{
   "name":"String",    
   "school":"String",
   "grade": "INT",
   "classes": "INT",
   "number": "INT",
}
```
- Response
```json
SUCCESS{ "code": 200, "message": "Success"}
FAIL {
    "code": 500, "message": "Server Error",
    "code": 401, "message": "본인의 ID가 아닙니다."
}
```
### 회원 정보 삭제
- URL
```
DELETE /users/<string:user_id>
```
- Request
```json
{
    "user_id":"String"
}
```
- Response
```json
SUCCESS{ "code": 200, "message": "Success"}
FAIL {
    "code": 500, "message": "Server Error",
    "code": 401, "message": "본인의 ID가 아닙니다."
}
```
### 회원 ID 가져오기  
- URL
```
GET /users/userInfo
```
- Request
```Header
"Authentication" : "accessToken"
```
- Response
```json
SUCCESS{ "userId" : "String"}
FAIL {
    "code": 403, "error" : "Forbidden", "message": "Access Denied."
}
```
---
## 시간표

### 일주일 시간표 보기

- URL
  - Date에 월요일 날짜를 주세요.

```
GET /timetable/week?<string:school>&<int:grade>&<int:classes>&<date:date>
```

- Request

```
X
```

- Response


``` json
SUCCESS{
    [
        {
            "id": 608,
            "subject": "국어",
            "startTime": "09:00",
            "endTime": "09:40",
            "day": "월"
        },
        {
            "id": 609,
            "subject": "체육",
            "startTime": "09:50",
            "endTime": "10:30",
            "day": "월"
        },
        {
            "id": 610,
            "subject": "과학",
            "startTime": "10:40",
            "endTime": "11:20",
            "day": "월"
        },
	    ...
    ]
}
FAIL{
    "code": 404, "message": "해당하는 수업이 없습니다."
}
```

### 오늘 시간표 보기

- URL
  - Date에 시간표를 보고싶은 날짜를 주세요.

```
GET /timetable/today?<string:school>&<int:grade>&<int:classes>&<date:date>
```

- Request


```
X
```

- Response

``` json
SUCCESS{
    [
        {
            "id": 608,
            "subject": "국어",
            "startTime": "09:00",
            "endTime": "09:40",
            "day": "월"
        },
        {
            "id": 609,
            "subject": "체육",
            "startTime": "09:50",
            "endTime": "10:30",
            "day": "월"
        },
        {
            "id": 610,
            "subject": "과학",
            "startTime": "10:40",
            "endTime": "11:20",
            "day": "월"
        },
    	...
    ]
}
FAIL{
    "code": 404, "message": "해당하는 수업이 없습니다."
}
```

### 일주일 시간표 만들기

###### 참고

- 시간이 겹치는 부분 / 종료시간이 시작시간보다 뒤일 경우에 대한 처리는 안했습니다.

- URL

```
POST /timetable/week
```

- Request
  - **JSON으로 보내주세요**
  - year : 연도, week : 해당 연도의 몇주차인지
  - start_time, end_time : 시:분 (시는 0-24로 써주세요 / **00:00 이 형식 꼭 지켜주셔야 합니다!** / 초는 무조건 0으로 들어갑니다)

```JSON
{
	"year" : 2022,
	"week" : 8,
	"school" : "싸피초등학교",
	"grade" : 1,
	"classes" : 1,
	"1" : [
					{
						"subject": "국어",
						"start_time" : "09:00",
						"end_time" : "09:40"
					},
			],
	"2" : [
					{
						"subject": "사회",
						"start_time" : "09:00",
						"end_time" : "09:40"
					},
					{
						"subject": "과학",
						"start_time" : "09:50",
						"end_time" : "10:30"
					}		
			],
	"3" : [],
	"4" : [],
	"5" : [
					{
						"subject": "음악",
						"start_time" : "09:00",
						"end_time" : "09:40"
					}
			]
}
```

- Response

``` json
SUCCESS{
    "code": 200, "message": "Ok",
}
```

### 일주일 시간표 수정

- URL

```
PUT /timetable/week
```

- Request
  - **JSON으로 보내주세요**
  - year : 연도, week : 해당 연도의 몇주차인지
  - start_time, end_time : 시:분 (시는 0-24로 써주세요 / **00:00 이 형식 꼭 지켜주셔야 합니다!** / 초는 무조건 0으로 들어갑니다)

```JSON
{
	"year" : 2022,
	"week" : 8,
	"school" : "싸피초등학교",
	"grade" : 1,
	"classes" : 1,
	"1" : [
					{
						"subject": "국어",
						"start_time" : "09:00",
						"end_time" : "09:40"
					},
			],
	"2" : [
					{
						"subject": "사회",
						"start_time" : "09:00",
						"end_time" : "09:40"
					},
					{
						"subject": "과학",
						"start_time" : "09:50",
						"end_time" : "10:30"
					}		
			],
	"3" : [],
	"4" : [],
	"5" : [
					{
						"subject": "음악",
						"start_time" : "09:00",
						"end_time" : "09:40"
					}
			]
}
```

- Response

``` json
SUCCESS{
    "code": 200, "message": "Ok",
}
```

---

### 게시판

### 반

### 반 게시판 전체 목록

- URL

```
GET /board/class?<string:school>&<int:grade>&<int:classes>&<int:page>&<int:size>
```

- Request

```
X
```

- Response

``` json
{
	"content": [
		{
			"id": 332,
			"category": "question",
			"title": "박윤지의 질문",
			"content": "",
			"user": {
				"id": 67,
				"name": "박윤지",
				"position": "S",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 6,
				"userId": "ssafy4",
				"email": "ssafy4@ggg.com",
				"password": "$2a$10$6nrhXa/hk0V8kxlGktvkEOTkDiuTUvkL7PAoU8BQxhTsDGgSgo9pS",
				"birth": "2022-02-17"
			},
			"school": "싸피초등학교",
			"grade": 1,
			"classes": 1,
			"date": "2022-02-18 00:30:10",
			"viewcount": 2
		},
		{
			"id": 331,
			"category": "notice",
			"title": "2월 20일 알림장",
			"content": "1. 수수께끼 풀어오세요~\r\n2. 주말에 재밌게 놀고오기^^",
			"user": {
				"id": 64,
				"name": "이상백",
				"position": "T",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 0,
				"userId": "ssafy1",
				"email": "ssafy1@ggg.com",
				"password": "$2a$10$JhOk0tpCrKi1i508NEE2w.CGf9JHtJjf5X91KCMvePsgmMtHAekYa",
				"birth": "2022-02-17"
			},
			"school": "싸피초등학교",
			"grade": 1,
			"classes": 1,
			"date": "2022-02-18 00:24:00",
			"viewcount": 31
		},
        ...
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 1,
		"pageSize": 5,
		"offset": 5,
		"paged": true,
		"unpaged": false
	},
	"last": false,
	"totalPages": 3,
	"totalElements": 14,
	"first": false,
	"numberOfElements": 5,
	"size": 5,
	"number": 1,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"empty": false
}
```

### 반 게시판 카테고리글 목록

- URL

```
GET /board/class/category?<string:school>&<int:grade>&<int:classes>&<string:category>&<int:page>&<int:size>
```

- Request

```
X
```

- Response

``` json
{
	"content": [
		{
			"id": 337,
			"category": "assignment",
			"title": "오늘의 수학 숙제",
			"content": "1부터 20까지 숫자 쓰기 연습을 해보세요.",
			"user": {
				"id": 64,
				"name": "이상백",
				"position": "T",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 0,
				"userId": "ssafy1",
				"email": "ssafy1@ggg.com",
				"password": "$2a$10$JhOk0tpCrKi1i508NEE2w.CGf9JHtJjf5X91KCMvePsgmMtHAekYa",
				"birth": "2022-02-17"
			},
			"school": "싸피초등학교",
			"grade": 1,
			"classes": 1,
			"date": "2022-02-18 10:27:03",
			"viewcount": 18
		},
		{
			"id": 323,
			"category": "assignment",
			"title": "글씨 예쁘게 써오기",
			"content": "아래 단어들을 예쁘게 써와주세요~!\r\n1. 구름\r\n2. 바다\r\n3. 사랑\r\n4. 무지개",
			"user": {
				"id": 64,
				"name": "이상백",
				"position": "T",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 0,
				"userId": "ssafy1",
				"email": "ssafy1@ggg.com",
				"password": "$2a$10$JhOk0tpCrKi1i508NEE2w.CGf9JHtJjf5X91KCMvePsgmMtHAekYa",
				"birth": "2022-02-17"
			},
			"school": "싸피초등학교",
			"grade": 1,
			"classes": 1,
			"date": "2022-02-18 00:12:27",
			"viewcount": 46
		},
        ...
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 0,
		"pageSize": 5,
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalPages": 1,
	"totalElements": 2,
	"first": true,
	"numberOfElements": 2,
	"size": 5,
	"number": 0,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"empty": false
}
```

### 반 게시판 카테고리, 유저로 조회

- URL

```
GET /board/class/category/user?<string:school>&<int:grade>&<int:classes>&<string:category>&<string:userId>&<int:page>&<int:size>
```

- Request

```
X
```

- Response

``` json
{
	"content": [
		{
			"id": 332,
			"category": "question",
			"title": "박윤지의 질문",
			"content": "",
			"user": {
				"id": 67,
				"name": "박윤지",
				"position": "S",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 6,
				"userId": "ssafy4",
				"email": "ssafy4@ggg.com",
				"password": "$2a$10$6nrhXa/hk0V8kxlGktvkEOTkDiuTUvkL7PAoU8BQxhTsDGgSgo9pS",
				"birth": "2022-02-17"
			},
			"school": "싸피초등학교",
			"grade": 1,
			"classes": 1,
			"date": "2022-02-18 00:30:10",
			"viewcount": 2
		},
        ...
	],
	"pageable": {
		"sort": {
			"sorted": false,
			"unsorted": true,
			"empty": true
		},
		"pageNumber": 0,
		"pageSize": 5,
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalPages": 1,
	"totalElements": 1,
	"first": true,
	"numberOfElements": 1,
	"size": 5,
	"number": 0,
	"sort": {
		"sorted": false,
		"unsorted": true,
		"empty": true
	},
	"empty": false
}
```

### 반 게시판 글 상세

- URL
  - 숙제게시판을 호출할 때만 `userId`파라미터가 들어갑니다. 다른 게시판은 보내지 않습니다.

```
(숙제게시판) GET /board/class/detail?<string:school>&<int:grade>&<int:classes>&<int:id>&<String:userId>
(다른게시판) GET /board/class/detail?<string:school>&<int:grade>&<int:classes>&<int:id>
```

- Request

```
X
```

- Response
  - 파일, 댓글, 글정보 같이감

``` json
{
	"comments": [
		{
			"id": 108,
			"createdAt": "2022-02-18 00:29:09",
			"content": "",
			"parent_id": 0,
			"user": {
				"id": 67,
				"name": "박윤지",
				"position": "S",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 6,
				"userId": "ssafy4",
				"email": "ssafy4@ggg.com",
				"password": "$2a$10$6nrhXa/hk0V8kxlGktvkEOTkDiuTUvkL7PAoU8BQxhTsDGgSgo9pS",
				"birth": "2022-02-17"
			}
		},
		{
			"id": 110,
			"createdAt": "2022-02-18 00:31:40",
			"content": "",
			"parent_id": 0,
			"user": {
				"id": 68,
				"name": "권영현",
				"position": "S",
				"school": "싸피초등학교",
				"grade": 1,
				"classes": 1,
				"number": 4,
				"userId": "ssafy5",
				"email": "ssafy5@ggg.com",
				"password": "$2a$10$JLI8qO/Fs3dDZMhc6QUjbO1sHNph7oRScTRYYxDoKKvz8pl5osI5.",
				"birth": "2022-02-17"
			}
		},
		...
	],
	"files": [
		{
			"id": 189,
			"boardId": 323,
			"commentId": 108,
			"original_file_name": "박윤지의 숙제",
			"stored_file_path": "images/20220217/2127312029666292.jpg",
			"file_size": 49789,
			"isDeleted": "N"
		},
		{
			"id": 193,
			"boardId": 323,
			"commentId": 110,
			"original_file_name": "권영현의 숙제",
			"stored_file_path": "images/20220217/2127463399972254.jpg",
			"file_size": 48386,
			"isDeleted": "N"
		},
		...
	],
	"board": {
		"id": 323,
		"category": "assignment",
		"title": "글씨 예쁘게 써오기",
		"content": "아래 단어들을 예쁘게 써와주세요~!\r\n1. 구름\r\n2. 바다\r\n3. 사랑\r\n4. 무지개",
		"user": {
			"id": 64,
			"name": "이상백",
			"position": "T",
			"school": "싸피초등학교",
			"grade": 1,
			"classes": 1,
			"number": 0,
			"userId": "ssafy1",
			"email": "ssafy1@ggg.com",
			"password": "$2a$10$JhOk0tpCrKi1i508NEE2w.CGf9JHtJjf5X91KCMvePsgmMtHAekYa",
			"birth": "2022-02-17"
		},
		"school": "싸피초등학교",
		"grade": 1,
		"classes": 1,
		"date": "2022-02-18 00:12:27",
		"viewcount": 48
	}
}
```

### 반 게시판 글 작성

- URL
  - files는 보내지않거나, 다수 보내셔도 됩니다.

```
POST /board/class?<string:category>&<string:title>&<string:content>&<string:school>&<int:grade>&<int:classes>&<string:userId>&<files:multipartfile>
```

- Request

```json
X
```

- Response

``` json
SUCCESS{
    "code":200,
}
```

### 반 게시판 글 수정

- URL
  - fileIsChanged 꼭 보내셔야 하며 file 변화가 있을 때 "Y" 로 보냅니다.
  - files는 보내지않거나, 다수 보내셔도 됩니다.

```
PUT /board/class?<int:id>&<string:category>&<string:title>&<string:content>&<string:school>&<int:grade>&<int:classes>&<string:fileIsChanged>&<files:multipartfile>
```

- Request


```json
X
```

- Response

``` json
{
	"id": 119,
	"category": "테스트",
	"title": "글수정~~",
	"content": "게시판 내용 테스트 수정2",
	"user": {
		"id": 1,
		"name": "권영현",
		"position": "A",
		"school": "천안초등학교",
		"grade": 6,
		"classes": 8,
		"number": 1012345678,
		"userId": "yunghun97",
		"email": "yunghun97@naver.com",
		"password": "password97",
		"birth": "1997-01-05"
	},
	"school": "싸피초",
	"grade": 1,
	"classes": 1,
	"date": "2022-02-08 14:22:49",
	"viewcount": 1
}
```

### 반 게시판 글 삭제

- URL
  - id는 board_id 입니다.

```
DELETE /board/class?<string:school>&<int:grade>&<int:classes>&<int:id>
```

- Request

```json
X
```

- Response

``` json
SUCCESS{
    "code":200,"message":"OK",
}
```

### 공지사항

### 공지사항 전체 목록

- URL

```
GET /board/notice?<int:page>&<int:size>&<string:sort>
```

- Request

```
X
```

- Response

  - content 안에 게시물 객체들이 들어있고
  - totalPages 가 전체 페이지 수입니다

```json
{
	"content": [
		{
			"id": 1,
			"title": "첫 공지사항",
			"content": "지켜",
			"user": {
				"id": 4,
				"name": "ㅁㄴㅇ",
				"position": "S",
				"school": "샘머리 초등학교",
				"grade": 3,
				"classes": 0,
				"number": 0,
				"userId": "id",
				"email": "ss@na.cm",
				"password": "$2a$10$by7/xTlAMLX4ETwXrP6fZ.WjU1j6rTOk.oi5L16wbo99ZeBmPPuIa",
				"birth": null
			},
			"date": "2022-02-13 22:14:33",
			"viewcount": 0
		},
        ...
	],
	"pageable": {
		"sort": {
			"empty": false,
			"sorted": true,
			"unsorted": false
		},
		"offset": 5,
		"pageNumber": 1,
		"pageSize": 5,
		"paged": true,
		"unpaged": false
	},
	"last": true,
	"totalPages": 2,
	"totalElements": 6,
	"size": 5,
	"number": 1,
	"sort": {
		"empty": false,
		"sorted": true,
		"unsorted": false
	},
	"first": false,
	"numberOfElements": 1,
	"empty": false
}
```

### 공지사항 글 작성

- URL
  - files는 보내지않거나, 다수 보내셔도 됩니다.

```
POST /board/notice?<string:title>&<string:content>&<string:userId>&<multipartfile:files>
```

- Request

```
X
```

- Response

``` json
SUCCESS{
    "code":200,
}
```

### 공지사항 글 수정

- URL
  - files는 보내지않거나, 다수 보내셔도 됩니다.

```
PUT /board/notice?<string:title>&<string:content>&<multipartfile:files>
```

- Request

```json
X
```

- Response

``` json
{
	"id": 3,
	"title": "공지사항",
	"content": "지켜",
	"user": {
		"id": 4,
		"name": "ㅁㄴㅇ",
		"position": "S",
		"school": "샘머리 초등학교",
		"grade": 3,
		"classes": 0,
		"number": 0,
		"userId": "id",
		"email": "ss@na.cm",
		"password": "$2a$10$by7/xTlAMLX4ETwXrP6fZ.WjU1j6rTOk.oi5L16wbo99ZeBmPPuIa",
		"birth": null
	},
	"date": "2022-02-13 22:18:21",
	"viewcount": 1
}
```

### 공지사항 글 삭제

- URL

```
DELETE /board/notice/{noticeId}
```

- Request

```json
X
```

- Response

``` json
SUCCESS{
    "code":200,"message":"OK",
}
```

### 공지사항 글 상세

- URL

```
GET /board/notice/{noticeId}
```

- Request

```
X
```

- Response
  - 파일, 글정보 같이감

``` json
{
	"files": [
		{
			"id": 2,
			"noticeId": 3,
			"original_file_name": "프레젠테이션1.pptx",
			"stored_file_path": "etc/20220213/160511547434300.pptx",
			"file_size": 30451,
			"isDeleted": "N"
		},
		{
			"id": 3,
			"noticeId": 3,
			"original_file_name": "candy.txt",
			"stored_file_path": "etc/20220213/160511548508800.txt",
			"file_size": 5,
			"isDeleted": "N"
		}
	],
	"board": {
		"id": 3,
		"title": "공지사항",
		"content": "지켜",
		"user": {
			"id": 4,
			"name": "ㅁㄴㅇ",
			"position": "S",
			"school": "샘머리 초등학교",
			"grade": 3,
			"classes": 0,
			"number": 0,
			"userId": "id",
			"email": "ss@na.cm",
			"password": "$2a$10$by7/xTlAMLX4ETwXrP6fZ.WjU1j6rTOk.oi5L16wbo99ZeBmPPuIa",
			"birth": null
		},
		"date": "2022-02-13 22:18:21",
		"viewcount": 0
	}
}
```

## 반 댓글

### 반 게시판 댓글 가져오기

- URL

```
GET /board/class/{boardId}/comment
```

- Request

```
X
```

- Response

``` json
{
	"comments": [
		{
			"id": 45,
			"createdAt": "2022-02-13 19:50:09",
			"content": "",
			"parent_id": 0,
			"user": {
				"id": 11,
				"name": "박싸피",
				"position": "T",
				"school": "노형 초등학교",
				"grade": 1,
				"classes": 1,
				"number": 0,
				"userId": "parkssafy",
				"email": "parkssafy@email.com",
				"password": "$2a$10$NZpEAgqvqc89B/WTslQZLO46YPqZQXJjJjq1Eg9JBGqmtb2E/pJRy",
				"birth": "1999-01-09"
			}
		},
		{
			"id": 53,
			"createdAt": "2022-02-13 21:17:51",
			"content": "참 잘했어요!",
			"parent_id": 45,
			"user": {
				"id": 11,
				"name": "박싸피",
				"position": "T",
				"school": "노형 초등학교",
				"grade": 1,
				"classes": 1,
				"number": 0,
				"userId": "parkssafy",
				"email": "parkssafy@email.com",
				"password": "$2a$10$NZpEAgqvqc89B/WTslQZLO46YPqZQXJjJjq1Eg9JBGqmtb2E/pJRy",
				"birth": "1999-01-09"
			}
		},
        ...
    ]
}
```

### 반 게시판 댓글 작성

- URL
  - userId : 로그인한 유저의 아이디
  - content : 댓글 내용
  - parent_id : 대댓글일 때, 부모 댓글의 pk
  - files : 보내지않거나, 다수 보내셔도 됩니다.
  - 대댓글  작성할 때 (`parent_id` 꼭 빼야합니다)

```
(댓글 작성시) POST /board/class/{boardId}/comment?<string:userId>&<string:content>&<int:parent_id>&<multipartfile:files>
(대댓글 작성시) POST /board/class/{boardId}/comment?<string:userId>&<string:content>&<multipartfile:files>
```

- Request

```
X
```

- Response

``` json
SUCCESS{
    "code": 200, "message": "OK",
}
```

### 반 게시판 댓글 수정

- URL
  - files : 보내지않거나, 다수 보내셔도 됩니다.

```
PUT /board/class/{boardId}/comment/{commentId}?<string:content>&<multipartfile:files>
```

- Request

```
X
```

- Response

``` json
{
	"id": 7,
	"createdAt": "2022-02-11 20:39:44",
	"content": "수정한거보이냐고!!!",
	"parent_id": 3,
	"user": {
		"id": 1,
		"name": "권영현",
		"position": "A",
		"school": "천안초등학교",
		"grade": 6,
		"classes": 8,
		"number": 1012345678,
		"userId": "yunghun97",
		"email": "yunghun97@naver.com",
		"password": "password97",
		"birth": "1997-01-05"
	}
}
```

### 반 게시판 댓글 삭제

- URL
  - boardId 정확하게 입력하셔야 합니다


```
DELETE /board/class/{boardId}/comment/{commentId}
```

- Request

```
X
```

- Response

``` json
SUCCESS{
    "code": 200, "message": "OK",
}
```

### 대댓글 보기 (숙제게시판용)

- URL

```
GET /board/class/{boardId}/comment/{commentId}
```

- Request

```
X
```

- Response

``` json
[
	{
		"id": 30,
		"createdAt": "2022-02-13 16:58:25",
		"content": "댓글",
		"parent_id": 28,
		"user": {
			"id": 1,
			"name": "권영현",
			"position": "A",
			"school": "천안초등학교",
			"grade": 6,
			"classes": 8,
			"number": 1012345678,
			"userId": "yunghun97",
			"email": "yunghun97@naver.com",
			"password": "password97",
			"birth": "1997-01-05"
		}
	},
	{
		"id": 31,
		"createdAt": "2022-02-13 16:58:26",
		"content": "댓글",
		"parent_id": 28,
		"user": {
			"id": 1,
			"name": "권영현",
			"position": "A",
			"school": "천안초등학교",
			"grade": 6,
			"classes": 8,
			"number": 1012345678,
			"userId": "yunghun97",
			"email": "yunghun97@naver.com",
			"password": "password97",
			"birth": "1997-01-05"
		}
	}
]
```

---

## 화상회의  
## 공통 HEADERS
{
    Authorization: Basic ```EncodeBase64(OPENVIDUAPP:ssafy)```
}

## Room
- Response
### 세션 생성
- URL
```
POST /lecture
```
- Request  
```json
{
    "customSessionId":  "mySessionId"
}
```
- Response  
```Session Object```
```json
{
    "id": "ses_YnDaGYNcd7",
    "object": "session",
    "createdAt": 1538481996019,
    "mediaMode": "ROUTED",
    "recordingMode": "MANUAL",
    "defaultRecordingProperties": {
        "name": "MyRecording",
        "hasAudio": true,
        "hasVideo": true,
        "outputMode": "COMPOSED",
        "recordingLayout": "BEST_FIT",
        "resolution": "1280x720",
        "frameRate": 25,
        "shmSize": 536870912,
        "mediaNode": "media_i-po39jr3e10rkjsdfj"
    },
    "customSessionId": "TestSession",
    "connections": {
        "numberOfElements": 0,
        "content": []
    },
    "recording": false,
    "forcedVideoCodec": "VP8",
    "allowTranscoding": false,
    "mediaNodeId": "media_i-po39jr3e10rkjsdfj"
}
```
### 세션 삭제
- URL
```
DELETE /lecture?string:sessionId>
```
- Request  
X
- Response  
```HTTP responses```
```
204
404
```
### 현재 생성된 모든 세션 정보 보기
- URL
```
GET /lecture
```
- Request
X
- Response
```json
{
    "numberOfElements": 0, // Total number of Sessions
    "content": []       // 	Array of Session objects
}

```

### 세션 입장
- URL
```
POST /lecture/connect
```
- Request
```json
{
    "customSessionId": "mysessionId" 
}
```
- Response  
```Connetion Object```
```json
{
    "id": "con_Xnxg19tonh",
    "object": "connection",
    "type": "WEBRTC",
    "status": "active",
    "sessionId": "ses_YnDaGYNcd7",
    "createdAt": 1538481999022,
    "activeAt": 1538481999843,
    "location": "Madrid, Spain",
    "ip": "37.122.145.190",
    "platform": "Chrome 85.0.4183.102 on Linux 64-bit",
    "token": "wss://localhost:4443?sessionId=TestSession&token=tok_AVe8o7iltWqtijyl&role=PUBLISHER&version=2.16.0&coturnIp=localhost&turnUsername=M2ALIY&turnCredential=7kfjy2",
    "serverData": "My Server Data",
    "clientData": "My Client Data",
    "record": true,
    "role": "PUBLISHER",
    "kurentoOptions": {
        "videoMaxRecvBandwidth": 1000,
        "videoMinRecvBandwidth": 300,
        "videoMaxSendBandwidth": 1000,
        "videoMinSendBandwidth": 300,
        "allowedFilters": [
            "GStreamerFilter",
            "ZBarFilter"
        ]
    },
    "rtspUri": null,
    "adaptativeBitrate": null,
    "onlyPlayWithSubscribers": null,
    "networkCache": null,
    "publishers": [
        {
            "createdAt": 1538481999710,
            "streamId": "str_CAM_NhxL_con_Xnxg19tonh",
            "mediaOptions": {
                "hasAudio": true,
                "audioActive": true,
                "hasVideo": true,
                "videoActive": true,
                "typeOfVideo": "CAMERA",
                "frameRate": 30,
                "videoDimensions": "{\"width\":640,\"height\":480}",
                "filter": {}
            }
        }
    ],
    "subscribers": [
        {
            "streamId": "str_MIC_JSXs_con_OV0CsFsykJ",
            "createdAt": 1538482000856
        }
    ]
}

```

### 세션 퇴장
- URL
```
DELETE /lecture?<string:sessionId>
```
- Request  
X
- Response  
```HTTP responses```  
```
204
400
404
```

### 방 입장 퇴장 기록 가져오기
- URL
```
GET /leture/history?<string:userId>
```
- Request  
X
- Response
```json
{
    "id": 1,    // HistoryId
    "join": "true",
    "link": "sessionId",
    "time": "2022-02-15",
    "user":{
        "id": 2,  // user 테이블의 Id
        "name": "권영현",
        "position": "S",
        "grade": 1,
        "classes": 2,
        "number": 5,
        "userId": "ssafy",
        "email": "ssafy@naver.com",
        "password": "JWT로 암호화된비번",
        "birth": 2000
    }
}
```

### 방 입장 퇴장 기록 남기기
- URL
```
POST /lecture/history?<string:userId>
```
- Request  
```json
{
    "link": "mysessionId",
    "join": "true"    // true면 입장 false 면 퇴장
}
```
- Response  
```HTTP responses```  
```
200
400
```

