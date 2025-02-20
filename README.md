# My Spring Boot Application

## 📌 Опис
Цей застосунок побудований на Spring Boot і підтримує роботу з різними базами даних (PostgreSQL, MySQL, SQL Server тощо).  
Swagger UI доступний за адресою: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## 🚀 Як запустити

### Варіант 1: Запуск вручну (без Docker)
1. Встанови **JDK 17** (або новішу версію).
2. Встанови **Maven** (якщо його ще немає).
3. Виконай команду для запуску:
   ```sh
   mvn spring-boot:run


### 🐳 Варіант 2: Запуск з Docker
1. Переконайся, що **Docker** встановлений і запущений на твоєму комп'ютері.
2. Створи **Docker-образ**:
   ```sh
   docker build -t my-spring-boot-app .


### 🐳 Варіант 3: Запуск з Docker-compose
1. Переконайся, що Docker та Docker Compose встановлені і запущені на твоєму комп'ютері.
2. Запусти **Docker Compose:**:
   ```sh
   docker-compose up
3. Для зупинки контейнерів:
   ```sh
   docker-compose down