pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        COMPOSE_PROJECT_NAME = 'selenium_tests'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Клонируем репозиторий
                    git branch: 'CI+Docker', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
                }
            }
        }

        stage('Set Up Docker Compose') {
            steps {
                script {
                    // Запускаем Docker Compose
                    bat 'docker-compose -f %DOCKER_COMPOSE_FILE% up -d'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Выполняем Maven тесты
                    bat 'docker-compose -f %DOCKER_COMPOSE_FILE% run --rm test'
                }
            }
        }

        stage('Tear Down') {
            steps {
                script {
                    // Останавливаем и удаляем контейнеры
                    bat 'docker-compose -f %DOCKER_COMPOSE_FILE% down'
                }
            }
        }
    }

    post {
        always {
            // В любом случае, по окончании работы мы можем остановить контейнеры
            script {
                bat 'docker-compose -f %DOCKER_COMPOSE_FILE% down'
            }
        }
    }
}