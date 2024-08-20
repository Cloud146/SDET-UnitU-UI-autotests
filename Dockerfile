# Используйте образ Maven как базовый
FROM maven:3.9.5-eclipse-temurin-21

# Установите рабочую директорию внутри контейнера
WORKDIR /project

# Копируйте файлы проекта и настройки Maven в рабочую директорию контейнера
COPY . /project

# Убедитесь, что папка .m2 существует
RUN mkdir -p /root/.m2

# Выполните установку зависимостей Maven (опционально)
# RUN mvn dependency:go-offline

# Выполните команду Maven по умолчанию
CMD ["mvn", "clean", "test", "-P", "env_docker_selenoid"]