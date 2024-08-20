pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Dmaven.repo.local=/home/app/.m2/repository'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'CI+Docker', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
            }
        }
        stage('Build and Run Containers') {
            steps {
                script {
                    // Остановка и удаление всех контейнеров перед запуском
                    powershell 'docker-compose down'
                    // Сборка и запуск контейнеров
                    powershell 'docker-compose up --build -d'
                    // Проверка статуса контейнеров
                    powershell 'docker-compose ps'
                    // Логи контейнеров для отладки
                    powershell 'docker-compose logs selenoid'
                    powershell 'docker-compose logs selenoid-ui'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    // Логи тестового контейнера перед запуском тестов
                    powershell 'docker-compose logs test'
                    // Запуск тестов
                    powershell 'docker-compose exec test mvn clean test'
                }
            }
        }
    }
    post {
        always {
            // Завершение работы и удаление всех контейнеров
            powershell 'docker-compose down -v'
        }
    }
}