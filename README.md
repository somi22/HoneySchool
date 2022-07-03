# Honey School (꿀잼스쿨)

## :hatched_chick: 초등학생과 교사를 위한 화상 교육 플랫폼

> 계속되는 비대면 수업에 지친 **선생님**,<br>
  매일 엄마에게 끌려와 수업듣는게 질린 **초등학생**,<br>
  이제는 초등 맞춤형 교육 플랫폼 **꿀잼스쿨**이 해결하겠습니다.

![honeyschool](README.assets/honeyschool.png)
<!-- <img alt="logo" src="README.assets/logo.png" width="500" height="500"/> -->

<br>

## :hatching_chick: 서비스 소개
초등학생의 눈높이에 맞지 않는 기존의 비대면 수업 서비스, 사용하기 힘들고 번거로우셨죠?

꿀잼스쿨을 사용하여 직접 손으로 숙제하고, 음성을 녹음해서 선생님께 질문해보세요.

더 쉽고 재미있는 공부 환경, 꿀잼스쿨이 제공해드립니다!

<br>

## :fireworks: 서비스 특징
- 초등학생의 눈높이에 맞추어 마우스, 키보드 사용을 최소화
- 화상 수업 시 집중력을 높일 수 있는 기능 제공
- 아기자기하고 직관적인 UI/UX

<br>

## :star2: 주요 기능

| 기능 | 설명 |
|:--------|:--------|
| 메인 페이지 | 오늘의 시간표와 요일 표시 |
| 화상 수업 | 1. 선생님이 시간표 작성<br>2. 현재 시간에 맞는 수업 개설 및 참석 버튼 활성화<br>3. 기능<br>- 손들기: 화면에 손 모양이 표시됨<br>- 자리비움 알림: 학생이 누르면 선생님에게 알림이 뜸<br>- 퀴즈 내기: 선생님이 퀴즈를 입력하면 학생들에게 문제가 보임<br>- 화면 공유: 선생님이 화면을 학생들에게 공유할 수 있음 |
| 전체 게시판 | 전체 카테고리의 글을 작성, 조회할 수 있음 |
| 숙제 게시판 | 1. 선생님이 게시판에 숙제 카테고리 글 작성<br>2. 학생이 숙제 작성 후 제출<br>3. 숙제 이미지 업로드 후 댓글로 작성됨<br>4. 선생님이 대댓글로 피드백<br>5. 숙제 창에서 피드백 확인 가능 |
| 질문 게시판 | 1. 학생이 질문 내용을 음성 녹음<br>2. 녹음 파일 업로드 후 게시판에 질문글 작성됨<br>3. 선생님이 댓글로 피드백<br>4. 질문 창에서 피드백 확인 가능 |
| 알림장 & 유인물 &<br> 사진첩 게시판 | 1. 선생님이 게시판에 알림장 카테고리 글 작성<br>2. 알림장 항목에서도 조회 가능 |

<br>

## :sparkles: 사용 모습

[전체 서비스 화면 보기](README.assets/service/service.md)

<br>

## :computer: 개발 환경

### :last_quarter_moon: Front-end

   - Vue3
   - Vuex4
   - Typescript
   - Bootstrap

### :first_quarter_moon: Back-end

   - Spring Boot
   - Spring Boot JPA
   - Spring Security
   - Java 11
   - OpenVidu
   - Lombok

### :floppy_disk: DB

   - MariaDB

### :tv: 기타 기술

   - WebRTC(OpenVidu)
   - AWS EC2
   - Docker
   - Nginx

### :family: 협업 툴

   - GitLab
   - Webex
   - Mattermost
   - Jira
   - Notion
   - Discord

<br>

## :earth_asia: 아키텍처
![architecture](README.assets/architecture.PNG)

<br>

## :raised_hands: Git branch 구조

- master
- develop (default branch)
    - front
        - feature/A
        - feature/B
        - feature/C
    - back
        - feature/A
        - feature/B
        - feature/C


<br>

## :raised_hands: Git 컨벤션
> **태그: 메세지 #Jira이슈번호**<br>
ex) Feat: “추가 get data api 함수 #S06P11B201-9
#### 태그
| 태그 이름 | 설명 |
| --- | --- |
| Feat | 새로운 기능을 추가할 경우 |
| Fix | 버그를 고친 경우 |
| Edit | 코드를 수정한 경우 |
| Design | CSS 등 사용자 UI 디자인 변경 |
| !BREAKING CHANGE | 커다란 API 변경의 경우 |
| !HOTFIX | 급하게 치명적인 버그를 고쳐야 하는 경우 |
| Style | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| Refactor | 프로덕션 코드 리팩토링 |
| Comment | 필요한 주석 추가 및 변경 |
| Docs | 문서를 수정한 경우 |
| Test | 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X) |
| Chore | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우 |
| Rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
| Remove | 파일을 삭제하는 작업만 수행한 경우 |

<br>

## :page_facing_up: 구동 메뉴얼

## FrontEnd 설정

1. 소스 다운 받기

```bash
git clone https://github.com/somi22/honeyschool.git
# 현재 /home/ubuntu/honeyschool 폴더에 받았다고 가정하고 진행
# 받으면 S06P12B201 폴더 생성 됨
```

2. 이동하기

```bash
# 이동
cd /var/www/html
# 기존의 dist 폴더 지워주기
rm -rf dist
# 새로 dist 폴더 바꿔주기
cp -r /home/ubuntu/honeyschool/S06P12B201/frontend/dist .
```

3. 도메인 접속해서 작동 유무 확인

<br>

## BackEnd 설정

1. 6단계에서 소스 다운 받았다고 가정 및 Openvidu 설치 시 사용한 p12키를 사용하여 SSL 구동합니다.

```bash
# 백엔드 소스 안에 진입 & 현재 
cd /home/ubuntu/honeyschool/S06P12B201/Backend/src/main/resources
# Openvidu 에서 사용한 키 복사
cp /home/ubuntu/opt/openvidu/certificates/live/경로
# application.properties 수정
nano application.properties

# SSL 인증 부분 수정
server.ssl.key-store=classpath:경로 # classpath:{키이름} classpath: src/main/resouces의 경로를 나타낸다. 
server.ssl.key-store-type=
server.ssl.key-store-password=  # p12 키 생성하면서 사용한 비밀번호
```

2. 빌드하기

```bash
# 백엔드 프로젝트 최상위 폴더로 이동
cd /home/ubuntu/honeyschool/S06P12B201/Backend
# 권한 부여하기
chmod 700 gradlew
# build 하기
./gradlew clean build # 성공시 build 생성
# jar 파일 생성확인
cd /build/libs
ls
# Backend-0.0.1-SNAPSHOT.jar 파일 생성확인

# 구동확인
java -jar Backend-0.0.1-SNAPSHOT.jar
# 에러 없으면 build 성공
```

3. DockerFile 만들기

```bash
nano Dockerfile  # f 소문자로 해야합니다!

#아래 내용 작성
FROM openjdk:11
ARG JAR_FILE=./Backend-0.0.1-SNAPSHOT.jar
copy ${JAR_FILE} honeyschool.jar
ENTRYPOINT ["java", "-jar", "honeyschool.jar"]
EXPOSE 9999

# 설명
## FROM 명령어 -> 스프링 부트 애플레케이션이 돌아갈 베이스 이미지를 의미합니다. 베이스 이미지는 docker hub 사이트 참조
## ARG 환경변수를 만들어 준다. -> jar 파일의 위치를 정해줌
## COPY jar 파일을 honeyschool.jar이라는 이름으로 복사
## ENTRYPOINT 애플리케이션을 실행시킬 명령어
## EXPOSE 애플리케이션이 사용하는 포트 
```

4. Docker Image 만들기

```bash
docker build -t yunghun97/test .

```

5. Docker 컨테이너 기본 실행 시 (컨테이너 삭제 시 데이터 날라감)

```bash
docker run --name {원하는 컨테이너 이름} -d -p 9999:9999 {이미지 이름}

## -d 는 백그라운드에서 작동
## -p IN:OUT IN으로 들어오는 포트를 OUT 포트로 매핑해준다.
```

<br>

### 도커 Volume 생성 및 마운트 하기

1. 불륨 생성하기

```bash
docker volume create {volume 명}

# docker volume create files
```

2. 불륨 생성확인

```bash
docker volume ls

```

- 결과<br>
   ![불륨확인](https://user-images.githubusercontent.com/71022555/154391203-37f90b1c-22d9-4e44-afb5-eab5765bd5e7.png)  

3. 불륨 정보 확인

```bash
docker volume inspect {files}
```

- 결과<br>
   ![불륨정보확인](https://user-images.githubusercontent.com/71022555/154391362-2e440cde-a2d7-4c15-a3e6-a64c333a7515.png)  

4. 불륨 마운트

```bash
docker run -v {불륨이름}:{마운트할 컨테이너 내부 경로} --name {컨테이너이름} -d -p 9999:9999 {이미지이름}

# docker run -v files:/home/ubuntu/honeyschool/file --name honeyschool_be -d -p 9999:9999 yunghun97/v0.9
```

5. 마운트 적용 확인

```bash
docker inspect {컨테이너 이름}

#docker inspect honeyschool_be
```

- 결과<br>
   ![마운트결과확인](https://user-images.githubusercontent.com/71022555/154392384-9da1c54b-f57e-43cd-a666-ed82082c2a36.png)  

6. 불륨 동기화 되었는지 확인하기

```bash
docker volume inspect files
#생략
"Mountpoint": "/var/lib/docker/volumes/{불륨명}/_data",
#생략

# 해당 폴더로 이동
cd /var/lib/docker/volumes/files/_data
# 파일 확인
ls
```

<br>

## :runner: 팀원 소개

|            박윤지             |                권영현                 |               박소미                |              이상백               |             정희연              |
| :---------------------------: | :-----------------------------------: | :---------------------------------: | :-------------------------------: | :-----------------------------: |
| ![yunji](README.assets/profile/yunji.png) | ![younghyun](README.assets/profile/younghyun.png) | ![somi](README.assets/profile/somi.png) | ![sangbaek](README.assets/profile/sangbaek.png) | ![huiyeon](README.assets/profile/huiyeon.png) |
|     **Leader & Backend**     |              **Backend & CI/CD**              |             **Frontend**             |        **Frontend**        |          **Backend**           |
|     학급 게시판, 수업 시간표     |              배포, 화상 수업              |             학급 게시판, 화상 수업             |        회원 관리, 화상 수업        |          회원 관리           |

<br>

## 출처

- UI/UX 디자인 일러스트 출처
  - https://www.streamlinehq.com
  - https://emojiterra.com/

