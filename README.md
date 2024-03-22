# Spring Boot 3 Web MVC 학습!

## 프로젝트 초기 설정 시 추가할 문법
- build.gradle 파일에 jsp 설정 추가
- (maven repository에는 spring boot 3에서 사용가능한 jstl 라이브러리가 없다. 그래서 수동으로 진행)
  ```
  implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
  implementation 'jakarta.servlet:jakarta.servlet-api'
  implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api'
  implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'
  ```