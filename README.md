# travel-service-api
trucmainguyen02@gmail.com   web                pbpfganrroiylmuq
trucmainguyen02@gmail.com  Email Sender        tizhunvykqcizxby

Travel project for Spring Boot

Admin@Admin MINGW64 /d/DH20DT/HK8/TieuLuan/API/travel-service-api (main)
```
$ ./mvnw package
```

Running app Packages as a JAR File

```
java -jar target/travel-service-api-0.0.1-SNAPSHOT.jar

```

```
./mvnw spring-boot:run
```

```
./mvnw clean package
```

# Project - MVC

Từ client gọi server gọi thông qua Restful API

- website - view - gọi API

- controllers - API - endpoint(đường dẫn của API)

- services    - quan trọng xử lí nghiệp vụ

Ví dụ: register - đăng ký tài khoản

+ kiểm tra password, repassword giống nhau 
+ kiểm tra username tồn tại
+ thỏa mới gọi insert
+ không thỏa > return

- repositories    -   JPA - kết nối database, thư viện query

- entities(entity)  - table   - database

# Source

https://github.com/mysql/mysql-connector-j?tab=readme-ov-file

https://mkyong.com/spring-boot/package-javax-validation-constraints-does-not-exist/

query param

route param

request body

response body

GET | POST | DELETE | PUT

## Building a RESTful Web Service with Spring Boot Actuator

Hổ trợ endpoint mappings

https://spring.io/guides/gs/actuator-service/

## Building REST services with Spring

https://spring.io/guides/tutorials/rest/

# Generate Database Schema with Spring Data JPA

```
CREATE SCHEMA `db_travels` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
```

https://www.baeldung.com/spring-data-jpa-generate-db-schema

Code first database design and development using JPA

https://www.baeldung.com/spring-data-repositories#:~:text=CrudRepository%20provides%20CRUD%20functions,deleting%20records%20in%20a%20batch

LINQ - lambda - ORM - Fluent API


https://stackoverflow.com/questions/73017508/is-it-possible-to-use-lambda-expressions-in-spring-jpa

- Quan trọng
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query

https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html#query-by-example.fluent

```
// mô tả table, biến
// liệt kê api > input placeId
// database mẫu
```

Unit of work | Repository Pattern | Entity | ORM

ORM : Object-relational mapping

# access-control-allow-origin in spring boot rest api

Access to XMLHttpRequest from origin has been blocked by CORS

https://spring.io/guides/gs/rest-service-cors/

## Entity

https://github.com/Damiox/ecommerce-rest-spring-jpa/tree/master

https://github.com/Aman103767/E-Commerce-Application/tree/main

# Using DTO (Data Transfer Object) Projection — Mapping Entities to DTOs and vice versa in Java Spring Boot

entity to dto mapper spring boot

https://www.javaguides.net/2021/02/spring-boot-dto-example-entity-to-dto.html#google_vignette

https://medium.com/@bectorhimanshu/using-dto-data-transfer-object-projection-mapping-entities-to-dtos-and-vice-versa-in-java-a7a9fe6b50a4

https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application

Working with Date Parameters in Spring https://www.baeldung.com/spring-date-parameters

https://xinghua24.github.io/SpringBoot/Spring-Boot-Handling-Date-Request-Parameter/

https://www.geeksforgeeks.org/java-program-to-convert-date-to-string/

https://www.youtube.com/watch?v=bV85_gIrHTc

jackson

json


Focus https://themewagon.github.io/focus-2/table-datatable-basic.html

https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/

https://github.com/callicoder/jpa-hibernate-tutorials   

# Enabling Cross Origin Requests for a RESTful Web Service

https://spring.io/guides/gs/rest-service-cors#global-cors-configuration
Focus https://themewagon.github.io/focus-2/table-datatable-basic.html

# Spring Boot 3 + Spring Security 6 - JWT Authentication and Authorisation [NEW] [2023]

https://www.youtube.com/watch?v=KxqlJblhzfI

https://github.com/ali-bouali/spring-boot-3-jwt-security

# Quan trọng Auth - Login
https://www.youtube.com/playlist?list=PLmxVbmyIiPPuR3eysh7LxmAlN0f9GQRAl
https://www.youtube.com/watch?v=RnZmeczS_DI

https://github.com/hello-iftekhar/springJwt/blob/main/src/main/java/com/helloIftekhar/springJwt/service/AuthenticationService.java

Implementing Secure Refresh Tokens in Spring Boot | #1 | Spring Boot JWT

https://www.youtube.com/watch?v=nvwKwsJg89E

# Tiếng việt

https://viblo.asia/p/huong-dan-spring-security-jwt-json-web-token-hibernate-oOVlYGmoK8W

https://github.com/loda-kun/spring-boot-learning/blob/master/spring-security-hibernate-jwt/pom.xml

middleware

https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac

cors

https://github.com/callicoder/spring-security-react-ant-design-polls-app/blob/master/polling-app-server/src/main/java/com/example/polls/config/WebMvcConfig.java

# architecture spring boot api - reactjs
https://www.bezkoder.com/spring-boot-react-jwt-auth/

https://jwt.io/

How to generate a JWT access token with some custom claims in it?

## Difference Between permitAll() and anonymous() in Spring Security

https://www.baeldung.com/spring-security-permitall-vs-anonymous

# Spring Boot – Interceptor
https://hocspringboot.net/2021/04/17/spring-boot-interceptor/

# reset password

https://www.baeldung.com/spring-security-registration-i-forgot-my-password

https://github.com/Baeldung/spring-security-registration/blob/master/src/main/java/com/baeldung/persistence/model/PasswordResetToken.java

# Spring - No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread

# Thuật toán Dijkstra

https://niithanoi.edu.vn/shortest-path.html

```
Thuật toán được sử dụng trong đoạn code này là thuật toán Dijkstra. Đây là một thuật toán tìm đường đi ngắn nhất trong một đồ thị có trọng số (weighted graph).

Cụ thể, trong đoạn code này, chúng ta thực hiện các bước sau:

Khởi tạo đồ thị bằng cách tạo các đỉnh (Vert) và các cạnh (Edge) với trọng số tương ứng.
Trong phương thức ShortestPath(), chúng ta sử dụng thuật toán Dijkstra để tìm đường đi ngắn nhất từ một điểm bắt đầu (start) đến tất cả các điểm còn lại.
Thuật toán Dijkstra hoạt động bằng cách lặp qua các đỉnh chưa được thêm vào đường đi, tìm đỉnh kế tiếp với khoảng cách ngắn nhất, và cập nhật đường đi và tổng khoảng cách tương ứng.
Cuối cùng, phương thức ShortestPath() in ra đường đi ngắn nhất và tổng khoảng cách.
Thuật toán Dijkstra là một trong những thuật toán tìm đường đi ngắn nhất phổ biến và hiệu quả, đặc biệt khi làm việc với các đồ thị có trọng số.
```