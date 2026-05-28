@echo off

set SPRING_DATASOURCE_URL=jdbc:postgresql://ep-ancient-silence-ac8apy42-pooler.sa-east-1.aws.neon.tech/neondb?sslmode=require
set SPRING_DATASOURCE_USERNAME=neondb_owner
set SPRING_DATASOURCE_PASSWORD=npg_5evWKsotDk1b

.\mvnw.cmd spring-boot:run

pause
