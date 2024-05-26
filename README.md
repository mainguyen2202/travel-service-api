# travel-service-api
trucmainguyen02@gmail.com   web                pbpfganrroiylmuq
trucmainguyen02@gmail.com  Email Sender        tizhunvykqcizxby

Travel project for Spring Boot

Admin@Admin MINGW64 /d/DH20DT/HK8/TieuLuan/API/travel-service-api (main)
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