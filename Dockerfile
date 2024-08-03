# Используем образ Maven с Java 21
FROM maven:3.9.5-eclipse-temurin-21

# Устанавливаем необходимые зависимости
RUN apt-get update && apt-get install -y \
    curl \
    unzip

# Устанавливаем Allure
RUN curl -o allure.zip -sSL https://github.com/allure-framework/allure2/releases/download/2.13.8/allure-2.13.8.zip && \
    unzip allure.zip -d /opt && \
    ln -s /opt/allure-2.13.8/bin/allure /usr/bin/allure

# Копируем исходный код в контейнер
COPY . /app
WORKDIR /app

# Команда для запуска сборки и тестов
CMD ["mvn", "clean", "test"]