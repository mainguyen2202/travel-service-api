# travel-service-api
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

# Database

```
CREATE SCHEMA `db_travels` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
```