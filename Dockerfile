# Використовуємо базовий образ з Java 17
FROM eclipse-temurin:17-jdk

# Встановлюємо робочу директорію для контейнера
WORKDIR /app

# Копіюємо файл wait-for-it.sh в контейнер
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Копіюємо зібраний .jar файл в контейнер
COPY target/spring-db-app-0.0.1-SNAPSHOT.jar app.jar

# Відкриваємо порт 8080
EXPOSE 8080

# Використовуємо скрипт wait-for-it для очікування готовності PostgreSQL
ENTRYPOINT ["/wait-for-it.sh", "postgres_db_1:5432", "--", "java", "-jar", "/app/app.jar"]
