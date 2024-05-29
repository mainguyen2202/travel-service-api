# Hướng dẫn từ A-Z tự xây dựng ứng dụng với Docker
https://www.youtube.com/watch?v=Gh1Sgknc6Fg

# How to use MySQL with Docker and Docker compose a beginners guide
https://geshan.com.np/blog/2022/02/mysql-docker-compose/

https://www.youtube.com/watch?v=PDt58n_hIKM

# Docker Deployment |How to Deploy Your Spring Boot CRUD Project With MySQL Database in Docker
https://www.youtube.com/watch?v=U2GCM0GBzNI

FROM openjdk:21
ADD target/travel-app-docker.jar travel-app-docker.jar
ENTRYPOINT ["java","-jar","/travel-app-docker.jar"]

pom
</plugins>
sau
		<finalName>travel-app-docker</finalName>

        docker network

docker build -t myorg/travel-app-docker .

docker network create travel-network

docker run -p 8080:8080 myorg/travel-app-docker

# kết hợp tên và run
docker run -p 8080:8080 --name travel-network myorg/travel-app-docker

# Tutorial

https://docs.docker.com/desktop/install/windows-install/

https://viblo.asia/p/docker-va-nhung-nhung-lenh-co-ban-cho-nguoi-moi-tim-hieu-OeVKB6xyKkW

https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04#step-3-running-docker-compose

https://hub.docker.com/_/mysql

https://www.docker.com/blog/kickstart-your-spring-boot-application-development/

https://viblo.asia/p/tim-hieu-docker-compose-qua-cac-vi-du-cu-the-yMnKMvQEZ7P

https://spring.io/guides/topicals/spring-boot-docker

https://mkyong.com/spring-boot/spring-boot-spring-data-jpa-mysql-example/

https://medium.com/@anuradha.kadurugasyaya/dockerize-a-spring-boot-application-a6df520f8ffe

https://github.com/EnggAdda/SpringBootMYSQLDocker/blob/master/pom.xml