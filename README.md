# Spring Boot 3 Web MVC 학습!

## 프로젝트 초기 설정 시 추가할 문법
- build.gradle 파일에 jsp 설정 추가
- (maven repository에는 spring boot 3에서 사용가능한 jstl 라이브러리가 없다. 그래서 수동으로 진행)
  ```
  implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
  implementation 'jakarta.servlet:jakarta.servlet-api'
  implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api'
  implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'~~~~
  ```
  
<br>

- application.properties 파일에 추가(아래 2가지)

포트 번호 변경
```
# server port change
server.port =8181
```
외부에서 jsp 파일을 지목하지 못하도록 WEB-INF 폴더에 넣어 놓을 것이며, 파일은 .jsp로 끝날 것이라고 Spring에게 알려주는 세팅
```
# jsp view resolver setting
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```
